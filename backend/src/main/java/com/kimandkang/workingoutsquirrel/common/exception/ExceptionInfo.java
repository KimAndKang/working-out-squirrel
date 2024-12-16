package com.kimandkang.workingoutsquirrel.common.exception;

public interface ExceptionInfo {

    Status status();

    int exceptionCode();

    String message();
}
