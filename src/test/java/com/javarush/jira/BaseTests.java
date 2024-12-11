package com.javarush.jira;

import com.javarush.jira.mail.MailService;
import com.javarush.jira.mail.internal.MailListeners;
import com.javarush.jira.ref.ReferenceService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestConfig.class)
abstract class BaseTests {
    @MockBean
    protected MailService mailService;

    @MockBean
    protected ReferenceService referenceService;

    @MockBean
    protected MailListeners mailListeners;
}
