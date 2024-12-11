package com.javarush.jira.check.language;

import com.javarush.jira.mail.MailController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/ruen")
public class EmailsController {
    static final String LANGMAIL="/ruen";
    @Autowired
    private ResourceBundleMessageSource messageSource;
    @GetMapping("/email")
    public String mail() {
        log.info("site!!!");
        return "email-confirmation";
    }
    @GetMapping("/")
    public String index() {
        log.info("site!!!");
        return "index";
    }
}
