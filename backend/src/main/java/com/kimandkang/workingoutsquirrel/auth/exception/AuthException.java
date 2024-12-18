package com.kimandkang.workingoutsquirrel.auth.exception;

import com.kimandkang.workingoutsquirrel.common.exception.BaseException;
import com.kimandkang.workingoutsquirrel.common.exception.ExceptionInfo;

public class AuthException extends BaseException {

    public AuthException(ExceptionInfo exceptionType) {
        super(exceptionType);
    }
}
