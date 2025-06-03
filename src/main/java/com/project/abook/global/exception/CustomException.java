package com.project.abook.global.exception;

public class CustomException extends RuntimeException {

    private ErrorCode errorCode;

    private CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
