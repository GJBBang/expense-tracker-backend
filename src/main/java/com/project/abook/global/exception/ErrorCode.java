package com.project.abook.global.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // Global
    GLOBAL_INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러가 발생했습니다. 관리자에게 문의해주세요.", "GL01"),

    ;

    private final HttpStatus httpStatus;
    private final String message;
    private final String code;

    ErrorCode(HttpStatus httpStatus, String message, String code) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.code = code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
