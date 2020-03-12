package com.instagram.clone.account.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(
        exclude = {SecurityAutoConfiguration.class}
)
public class AccountQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountQueryApplication.class, args);
    }
}
