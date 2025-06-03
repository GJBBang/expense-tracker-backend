package com.project.abook.global.exception;

public class CustomException extends RuntimeException {

    private ErrorCode errorCode;

    private CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
