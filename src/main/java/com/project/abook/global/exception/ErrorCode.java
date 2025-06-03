package com.project.abook.global.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // Global
    GLOBAL_INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러가 발생했습니다. 관리자에게 문의해주세요.", "GL01"),

    // authority
    AUTHORITY_NOT_FOUND_BY_AUTHORITY_CODE(HttpStatus.NOT_FOUND, "존재하지 않는 권한코드입니다.", "A01"),
    AUTHORITY_ACCESS_DENIED(HttpStatus.UNAUTHORIZED, "접근 권한이 없는 권한코드입니다.", "A02"),
    AUTHORITY_ENTRY_POINT(HttpStatus.UNAUTHORIZED, "오류가 있는 권한코드입니다.", "A03"),

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
