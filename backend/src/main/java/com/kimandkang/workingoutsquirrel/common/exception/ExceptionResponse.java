package com.kimandkang.workingoutsquirrel.common.exception;

public record ExceptionResponse(
        int exceptionCode,
        String message
) {
}
