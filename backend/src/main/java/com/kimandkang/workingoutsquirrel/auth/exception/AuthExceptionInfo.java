package com.kimandkang.workingoutsquirrel.auth.exception;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.kimandkang.workingoutsquirrel.common.exception.ExceptionInfo;
import org.springframework.http.HttpStatus;

public enum AuthExceptionInfo implements ExceptionInfo {

    SIGNATURE_NOT_FOUND(UNAUTHORIZED, 2000, "JWT 서명을 확인하지 못했습니다."),
    MALFORMED_TOKEN(UNAUTHORIZED, 2001, "토큰이 위조 또는 변조되었습니다."),
    EXPIRED_TOKEN(UNAUTHORIZED, 2002, "이미 만료된 토큰입니다. 다시 로그인 바랍니다."),
    UNSUPPORTED_TOKEN(UNAUTHORIZED, 2003, "지원되지 않는 토큰입니다."),
    ILLEGAL_ARGUMENT(UNAUTHORIZED, 2004, "토큰이 아닙니다."),
    ;

    private final HttpStatus httpStatus;
    private final int exceptionCode;
    private final String message;

    AuthExceptionInfo(HttpStatus httpStatus, int exceptionCode, String message) {
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
