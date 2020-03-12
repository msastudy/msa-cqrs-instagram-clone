package com.instagram.clone.common.model.api;

import lombok.Getter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
public class ApiError {

    private final String description;

    public ApiError(Throwable throwable) {
        this(throwable.getMessage());
    }

    public ApiError(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .toString();
    }

}

