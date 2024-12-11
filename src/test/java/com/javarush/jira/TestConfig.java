package com.javarush.jira;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(
        basePackages = "com.javarush.jira"
)
public class TestConfig {
}
