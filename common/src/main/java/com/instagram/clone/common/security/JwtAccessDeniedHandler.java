package com.instagram.clone.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram.clone.common.model.api.ApiResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private static ApiResult E403 = ApiResult.ERROR("Authentication error (cause: forbidden)");

    private ObjectMapper om;

    public JwtAccessDeniedHandler(ObjectMapper om) {
        this.om = om;
    }

    @Override
    public void handle(
            HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(om.writeValueAsString(E403));
        response.getWriter().flush();
        response.getWriter().close();
    }

}