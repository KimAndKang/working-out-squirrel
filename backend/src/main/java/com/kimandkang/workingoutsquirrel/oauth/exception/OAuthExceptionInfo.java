package com.kimandkang.workingoutsquirrel.oauth.exception;

import com.kimandkang.workingoutsquirrel.common.exception.ExceptionInfo;
import org.springframework.http.HttpStatus;

public enum OAuthExceptionInfo implements ExceptionInfo {

    FAIL_TO_REQUEST_OAUTH_TOKEN(HttpStatus.BAD_REQUEST, 1000, "OAuth 토큰 요청에 실패했습니다."),
    FAIL_TO_REQUEST_OAUTH_USER_INFO(HttpStatus.BAD_REQUEST, 1001, "OAuth 유저 정보 요청에 실패했습니다."),
    UNSUPPORTED_PROVIDER(HttpStatus.BAD_REQUEST, 1002, "지원하지 않는 OAuth 로그인 플랫폼입니다."),
    ;

    private final HttpStatus httpStatus;
    private final int exceptionCode;
    private final String message;

    OAuthExceptionInfo(HttpStatus httpStatus, int exceptionCode, String message) {
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