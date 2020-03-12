package com.instagram.clone.common.security;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.util.StringUtils.isEmpty;

public class JwtInterceptor implements HandlerInterceptor {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final Pattern PATTERN_AUTHORIZATION_HEADER = Pattern.compile("[Bb]earer (.+)");
    public static final String ACCOUNT_ID = "accountId";
    public static final String USER_NAME = "userName";

    private JWT jwt;

    public JwtInterceptor(JWT jwt) {
        this.jwt = jwt;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = extractAccessToken(request.getHeader(AUTHORIZATION_HEADER));
        JWT.Claims claims = jwt.decodePayload(token);
        if (isEmpty(claims.userKey) || isEmpty(claims.userName)) {
            throw new IllegalArgumentException("Invalid token. token:" + token);
        }
        request.setAttribute(ACCOUNT_ID, claims.userKey);
        request.setAttribute(USER_NAME, claims.userName);
        return true;
    }

    private String extractAccessToken(String header) {
        if (isEmpty(header)) {
            throw new IllegalArgumentException("Invalid authorization header. header:" + header);
        }
        final Matcher matcher = PATTERN_AUTHORIZATION_HEADER.matcher(header);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid authorization header. header:" + header);
        }
        return matcher.group(1);
    }
}
