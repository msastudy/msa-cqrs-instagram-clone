package com.instagram.clone.common.security;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Setter
@Getter
public class AuthenticationRequest {

    private String principal;
    private String credentials;
    private String userAgent;

    public AuthenticationRequest() {}

    AuthenticationRequest(String principal, String credentials, String userAgent)  {
        this.principal = principal;
        this.credentials = credentials;
        this.userAgent = userAgent;
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
    }
}
