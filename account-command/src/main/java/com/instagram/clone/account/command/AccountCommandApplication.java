package com.instagram.clone.account.command;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(
        exclude = {SecurityAutoConfiguration.class}
)
public class AccountCommandApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountCommandApplication.class, args);
    }
}
