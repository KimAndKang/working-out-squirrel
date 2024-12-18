package com.kimandkang.workingoutsquirrel.redis.exception;

import com.kimandkang.workingoutsquirrel.common.exception.BaseException;
import com.kimandkang.workingoutsquirrel.common.exception.ExceptionInfo;

public class TokenException extends BaseException {

    public TokenException(ExceptionInfo exceptionInfo) {
        super(exceptionInfo);
    }
}
