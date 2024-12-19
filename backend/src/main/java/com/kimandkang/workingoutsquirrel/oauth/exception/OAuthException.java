package com.kimandkang.workingoutsquirrel.oauth.exception;

import com.kimandkang.workingoutsquirrel.common.exception.BaseException;
import com.kimandkang.workingoutsquirrel.common.exception.ExceptionInfo;

public class OAuthException extends BaseException {

    public OAuthException(ExceptionInfo exceptionInfo) {
        super(exceptionInfo);
    }
}
