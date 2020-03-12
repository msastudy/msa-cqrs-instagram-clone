package com.instagram.clone.account.query.common.exception;

import com.instagram.clone.common.model.api.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError defaultExceptionHandler(Exception exception) {
        log.error("[! Exception !]: {}", exception.getMessage(), exception);
        return new ApiError("internal server_error");
    }
}
