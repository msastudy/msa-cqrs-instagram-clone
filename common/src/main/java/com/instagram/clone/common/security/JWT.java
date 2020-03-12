package com.instagram.clone.common.security;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Arrays;
import java.util.Date;

public final class JWT {

    private static final String USER_KEY = "userKey";
    private static final String USER_NAME = "userName";
    private static final String USER_AGENT = "userAgent";
    private static final String ROLES = "roles";

    private final String issuer;

    private final String clientSecret;

    private final int expirySeconds;

    private final Algorithm algorithm;

    private final JWTVerifier jwtVerifier;

    public JWT(String issuer, String clientSecret, int expirySeconds) {
        this.issuer = issuer;
        this.clientSecret = clientSecret;
        this.expirySeconds = expirySeconds;
        this.algorithm = Algorithm.HMAC512(clientSecret);
        this.jwtVerifier = com.auth0.jwt.JWT.require(algorithm)
                .withIssuer(issuer)
                .build();
    }

    public String newToken(Claims claims) {
        Date now = new Date();
        JWTCreator.Builder builder = com.auth0.jwt.JWT.create();
        builder.withIssuer(issuer);
        builder.withIssuedAt(now);
        if (expirySeconds > 0) {
            builder.withExpiresAt(new Date(now.getTime() + expirySeconds * 1_000L));
        }
        builder.withClaim(USER_KEY, claims.userKey);
        builder.withClaim(USER_NAME, claims.userName);
        builder.withClaim(USER_AGENT, claims.userAgent);
        builder.withArrayClaim(ROLES, claims.roles);
        return builder.sign(algorithm);
    }

    public String refreshToken(String token) throws JWTVerificationException {
        Claims claims = verify(token);
        claims.eraseIat();
        claims.eraseExp();
        return newToken(claims);
    }

    public Claims verify(String token) throws JWTVerificationException {
        return new Claims(jwtVerifier.verify(token));
    }

    public Claims decodePayload(String token) throws JWTVerificationException {
        return new Claims(com.auth0.jwt.JWT.decode(token));
    }

    public String getIssuer() {
        return issuer;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public int getExpirySeconds() {
        return expirySeconds;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public JWTVerifier getJwtVerifier() {
        return jwtVerifier;
    }

    public static class Claims {
        String userKey;
        @Getter
        String userName;
        String userAgent;
        String[] roles;
        Date iat;
        Date exp;

        private Claims() {}

        Claims(DecodedJWT decodedJWT) {
            Claim userKey = decodedJWT.getClaim(USER_KEY);
            if (!userKey.isNull())
                this.userKey = userKey.asString();
            Claim userName = decodedJWT.getClaim(USER_NAME);
            if (!userName.isNull())
                this.userName = userName.asString();
            Claim userAgent = decodedJWT.getClaim(USER_AGENT);
            if(!userAgent.isNull())
                this.userAgent = userAgent.asString();
            Claim roles = decodedJWT.getClaim(ROLES);
            if (!roles.isNull())
                this.roles = roles.asArray(String.class);
            this.iat = decodedJWT.getIssuedAt();
            this.exp = decodedJWT.getExpiresAt();
        }

        public static Claims of(String  userKey, String userName, String userAgent, String[] roles) {
            Claims claims = new Claims();
            claims.userKey = userKey;
            claims.userName = userName;
            claims.userAgent = userAgent;
            claims.roles = roles;
            return claims;
        }


        public String getRepositoryKey(){
            return this.userName+":"+this.userAgent;
        }

        long iat() {
            return iat != null ? iat.getTime() : -1;
        }

        long exp() {
            return exp != null ? exp.getTime() : -1;
        }

        void eraseIat() {
            iat = null;
        }

        void eraseExp() {
            exp = null;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                    .append(USER_KEY, userKey)
                    .append(USER_NAME, userName)
                    .append(USER_AGENT, userAgent)
                    .append(ROLES, Arrays.toString(roles))
                    .append("iat", iat)
                    .append("exp", exp)
                    .toString();
        }
    }

}
