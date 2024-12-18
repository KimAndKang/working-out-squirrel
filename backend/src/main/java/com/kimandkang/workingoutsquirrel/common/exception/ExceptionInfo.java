package com.kimandkang.workingoutsquirrel.common.exception;

import org.springframework.http.HttpStatus;

public interface ExceptionInfo {

    HttpStatus httpStatus();

    int exceptionCode();

    String message();
}
