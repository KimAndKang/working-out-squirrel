package com.kimandkang.workingoutsquirrel.common.exception;


public class BaseException extends RuntimeException {

    private final ExceptionInfo exceptionInfo;

    protected BaseException(ExceptionInfo exceptionInfo) {
        super(exceptionInfo.message());
        this.exceptionInfo = exceptionInfo;
    }

    public ExceptionInfo getExceptionInfo() {
        return exceptionInfo;
    }
}
