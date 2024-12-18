package com.kimandkang.workingoutsquirrel.redis.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.kimandkang.workingoutsquirrel.common.exception.ExceptionInfo;
import org.springframework.http.HttpStatus;

public enum TokenExceptionInfo implements ExceptionInfo {

    NO_SAVE_REFRESH_TOKEN(INTERNAL_SERVER_ERROR, 3000, "리프레시 토큰이 저장되지 않았습니다."),
    ;

    private final HttpStatus httpStatus;
    private final int exceptionCode;
    private final String message;

    TokenExceptionInfo(HttpStatus httpStatus, int exceptionCode, String message) {
        this.httpStatus = httpStatus;
        this.exceptionCode = exceptionCode;
        this.message = message;
    }

    @Override
    public HttpStatus httpStatus() {
        return httpStatus;
    }

    @Override
    public int exceptionCode() {
        return exceptionCode;
    }

    @Override
    public String message() {
        return message;
    }
}