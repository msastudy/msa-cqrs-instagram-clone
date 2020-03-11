package com.instagram.clone.account.command.service;

import com.instagram.clone.common.model.security.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class JWTService {

    private final JWT jwt;

    public String generate(String accountId, String userName, String userAgent, String[] roles) {
        Assert.notNull(accountId, "'accountId' must not be null");
        Assert.notNull(userName, "'userName' must not be null");

        JWT.Claims claim = JWT.Claims.of(accountId, userName, userAgent, roles);

        return jwt.newToken(claim);
    }
}
