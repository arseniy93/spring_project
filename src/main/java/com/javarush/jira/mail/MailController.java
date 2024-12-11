package com.javarush.jira.mail;

import com.javarush.jira.common.error.ErrorMessageHandler;
import com.javarush.jira.login.AuthUser;
import com.javarush.jira.login.User;
import com.javarush.jira.profile.internal.web.AbstractProfileController;
import com.javarush.jira.ref.RefType;
import com.javarush.jira.ref.ReferenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = MailController.MAIL_URL)
public class MailController {
    static final String MAIL_URL = "/ui/mail/language";
    @Autowired
    private ResourceBundleMessageSource messageSource;//MessageSource
    private final ErrorMessageHandler errorMessageHandler;

    @GetMapping("/email")
    public String showConfirmationPage(Model model) {
        // Пример данных
        String messageEn = messageSource.getMessage("label.hello", null, new Locale("EN", "en"));
        String messageRu = messageSource.getMessage("label.hello", null, new Locale("RU", "ru"));

        log.info("English: " + messageEn);
        log.info("Russian: " + messageRu);

        User user = new User();
        user.setFirstName("John");
        model.addAttribute("user", user);
        model.addAttribute("confirmationUrl", "http://example.com/confirm");
        return "email-confirmation";
    }

    @GetMapping("/password")
    public String changePassword(Model model) {
        User user = new User();
        user.setFirstName("John");
        user.setEmail("john@mail.com");
        user.setPassword("123456");
        model.addAttribute("user", user);
        model.addAttribute("pass", user.getPassword());
        model.addAttribute("confirmationUrl", "http://example.com/confirm");
        return "password-reset";
    }

    @GetMapping("/index")
    public String index(Model model) {
        User user = new User();
        user.setFirstName("John");
        user.setEmail("john@mail.com");
        user.setPassword("123456");
        model.addAttribute("user", user);
        model.addAttribute("pass", user.getPassword());
        model.addAttribute("confirmationUrl", "http://example.com/confirm");
        return "index";
    }



//    @PostMapping("/reset-password")
//    public String resetPassword(@RequestParam("newPassword") String newPassword,
//                                @RequestParam("confirmPassword") String confirmPassword,
//                                Model model) {
//        if (!model.getAttribute("pass").equals(confirmPassword)) {
//            model.addAttribute("error", "Пароли не совпадают");
//            return "password-reset";
//        }
//        var user = (User) model.getAttribute("user");
//        user.setPassword(newPassword);
//        log.warn("Пароль изменен {}", newPassword);
//        return "redirect:/login";
//    }

}
