package com.javarush.jira.profile.internal.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.jira.AbstractControllerTest;
import com.javarush.jira.login.AuthUser;
import com.javarush.jira.login.Role;
import com.javarush.jira.login.User;
import com.javarush.jira.profile.ContactTo;
import com.javarush.jira.profile.ProfileTo;
import com.javarush.jira.profile.internal.ProfileMapper;
import com.javarush.jira.profile.internal.model.Profile;
import com.javarush.jira.profile.internal.service.ProfileService;
import com.javarush.jira.ref.RefTo;
import com.javarush.jira.ref.RefType;
import com.javarush.jira.ref.ReferenceService;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static com.javarush.jira.profile.internal.web.ProfileRestController.REST_URL;
import static org.mockito.BDDMockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.javarush.jira.common.util.JsonUtil;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.CookieGenerator;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ProfileRestControllerTest extends AbstractControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProfileService profileService;
    @MockBean
    private AuthUser authUser;

    @Test
    @WithUserDetails(value = "admin@gmail.com")
    @Transactional
    void update() throws Exception {
        ProfileTo profileTo = new ProfileTo(1L, Set.of("email"),
                Set.of(new ContactTo("github", "adminGitHub"),
                        new ContactTo("tg", "adminTg")));
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.writeValue(profileTo)))
                .andExpect(status().isInternalServerError())
                .andDo(print());
    }


    @Test
    @WithMockUser(username = "testUser", roles = {"ADMIN"})
    public void testIsAuthorized() throws Exception {
        // Замокированное поведение сервиса
        when(profileService.getOrCreate(1L)).thenReturn(new Profile());

    }

    @Test
    public void testGetUnAuthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL)
                        .with(httpBasic("username", "password")))
                .andExpect(status().isUnauthorized());
    }


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        User realUser = new User();
        realUser.setId(1L);
        realUser.setEmail("test@example.com");
        realUser.setPassword("password123");
        realUser.setRoles(Collections.singletonList(Role.ADMIN));
        AuthUser authUser = new AuthUser(realUser);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(authUser, null, List.of(new SimpleGrantedAuthority("ADMIN")))
        );

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(authUser, "n/a", List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))));
    }


    @Autowired
    private ProfileMapper mapper;

    @SpyBean
    private ReferenceService referenceService;


    @MockBean
    private Profile profile;

    @Test
    @WithMockUser(username = "testUser", roles = {"ADMIN"})
    public void testGetAuthorized() throws Exception {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Set mailNotifications for the profile
        profile.setMailNotifications(1);

        // Mock profile service behavior
        when(profileService.getOrCreate(1L)).thenReturn(profile);

        // Create a real instance of RefTo and configure it as needed
        RefTo refTo = new RefTo(1L, RefType.MAIL_NOTIFICATION, "1", "Test RefTo", null);

        // Create a compatible map for referenceService.getRefs
        Map<String, RefTo> refMap = new HashMap<>();
        refMap.put("1", refTo); // Use appropriate key and RefTo instance

        // Add debugging to verify the map content
        System.out.println("refMap: " + refMap);

        // Ensure the map is not null before stubbing
        if (refMap == null) {
            throw new IllegalStateException("refMap should not be null");
        }

        // Stub the referenceService.getRefs method
        when(referenceService.getRefs(RefType.MAIL_NOTIFICATION)).thenReturn(refMap);

        // Ensure the profile mapper uses the correct reference service
        ProfileTo profileTo = mapper.toTo(profile);
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(profileTo);

        // Perform the request
        mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

}









