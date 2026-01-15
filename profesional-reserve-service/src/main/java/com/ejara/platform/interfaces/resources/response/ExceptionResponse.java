package com.ejara.platform.interfaces.resources.response;

import lombok.Builder;

@Builder
public record ExceptionResponse(
        String message
) {
}
