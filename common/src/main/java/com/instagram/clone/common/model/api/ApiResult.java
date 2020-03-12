package com.instagram.clone.common.model.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> {

    @JsonUnwrapped
    private final T response;

    private final ApiError error;

    public static <T> ApiResult<T> OK(T response) {
        return new ApiResult<>(response, null);
    }

    public static ApiResult ERROR(Throwable throwable) {
        return new ApiResult<>(null, new ApiError(throwable));
    }

    public static ApiResult ERROR(String errorMessage) {
        return new ApiResult<>(null, new ApiError(errorMessage));
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .toString();
    }

}
