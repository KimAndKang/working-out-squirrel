package com.kimandkang.workingoutsquirrel.redis.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.kimandkang.workingoutsquirrel.common.exception.ExceptionInfo;
import org.springframework.http.HttpStatus;

public enum TokenExceptionInfo implements ExceptionInfo {

    NO_REFRESH_TOKEN(INTERNAL_SERVER_ERROR, 3000, "리프레시 토큰이 저장되지 않았습니다."),
    BLACKLISTED_ACCESS_TOKEN(UNAUTHORIZED, 3001, "블랙리스트 처리된 액세스 토큰입니다."),
    NOT_EQUAL_REFRESH_TOKEN(UNAUTHORIZED, 3002, "리프레시 토큰이 일치하지 않습니다."),
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