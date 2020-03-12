package com.instagram.clone.account.query.config;

import com.instagram.clone.common.security.JWT;
import com.instagram.clone.common.security.JwtInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Value("${jwt.token.issuer}") String issuer;
    @Value("${jwt.token.clientSecret}") String clientSecret;
    @Value("${jwt.token.expirySeconds}") int expirySeconds;

    @Bean
    public JWT jwt() {
        return new JWT(issuer, clientSecret, expirySeconds);
    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor(this.jwt());
    }
}