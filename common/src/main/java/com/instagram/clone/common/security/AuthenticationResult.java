package com.instagram.clone.common.security;

import lombok.Getter;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
public class AuthenticationResult {

    private final String apiToken;

    AuthenticationResult(String apiToken) {
        checkNotNull(apiToken, "apiToken must be provided.");

        this.apiToken = apiToken;
    }

}
