package com.project.abook.global.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // Global
    GLOBAL_INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러가 발생했습니다. 관리자에게 문의해주세요.", "GL01"),

    // access token
    INVALID_EXPIRED_JWT(HttpStatus.FORBIDDEN, "만료된 토큰입니다.", "J01"),
    INVALID_MALFORMED_JWT(HttpStatus.UNAUTHORIZED, "잘못된 토큰 서명입니다.", "J02"),
    INVALID_UNSUPPORTED_JWT(HttpStatus.UNAUTHORIZED, "지원하지 않는 토큰입니다.", "J03"),
    INVALID_ILLEGAL_ARGUMENT_JWT(HttpStatus.UNAUTHORIZED, "토큰이 잘못되었습니다.", "J04"),
    INVALID_LOGOUT_USER_JWT(HttpStatus.UNAUTHORIZED, "로그아웃된 유저입니다", "J05"),
    INVALID_NOT_FOUND_AUTHORITY(HttpStatus.NOT_FOUND, "토큰에 권한값이 존재하지 않습니다.", "J06"),

    // refresh token
    INVALID_NOT_MATCH_BY_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "리프레시 토큰이 일치하지 않습니다", "J10"),
    INVALID_EXPIRED_REFRESH_TOKEN(HttpStatus.FORBIDDEN, "만료된 리프레시 토큰입니다.", "J11"),
    INVALID_MALFORMED_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "잘못된 리프레시 토큰 서명입니다.", "J12"),
    INVALID_UNSUPPORTED_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "지원하지 않는 리프레시 토큰입니다.", "J13"),
    INVALID_ILLEGAL_ARGUMENT_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "리프레쉬 토큰이 잘못되었습니다.", "J14"),

    // authority
    AUTHORITY_NOT_FOUND_BY_AUTHORITY_CODE(HttpStatus.NOT_FOUND, "존재하지 않는 권한코드입니다.", "A01"),
    AUTHORITY_ACCESS_DENIED(HttpStatus.UNAUTHORIZED, "접근 권한이 없는 권한코드입니다.", "A02"),
    AUTHORITY_ENTRY_POINT(HttpStatus.UNAUTHORIZED, "오류가 있는 권한코드입니다.", "A03"),

    // member
    MEMBER_NOT_FOUND_BY_MEMBER_NAME(HttpStatus.NOT_FOUND, "존재하지 않는 아이디입니다.", "M01"),
    MEMBER_LOGIN_ID_DUPLICATED(HttpStatus.CONFLICT, "이미 존재하는 로그인 아이디 입니다.", "M02"),
    MEMBER_USERNAME_DUPLICATED(HttpStatus.CONFLICT, "이미 존재하는 닉네임 입니다.", "M03"),
    MEMBER_LOGIN_ERROR_BY_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다", "M04"),
    MEMBER_USERNAME_DUPLICATED_BECAUSE_OF_YOU(HttpStatus.CONFLICT, "회원님이 이미 사용 중인 닉네임입니다.", "M05"),
    MEMBER_PHONE_NUMBER_DUPLICATED(HttpStatus.CONFLICT, "이미 인증된 전화번호 입니다.", "M06"),
    MEMBER_NOT_SOCIAL_USER(HttpStatus.CONFLICT, "해당 회원은 소셜 아이디 로그인 회원이 아닙니다.", "M07"),
    MEMBER_NOT_YOUR_PHONE_NUMBER(HttpStatus.CONFLICT, "회원의 전화번호가 아닙니다.", "M08"),

    // exTracker
    EXTRACKER_NOT_FOUND_BY_EXTRACKER_ID(HttpStatus.NOT_FOUND, "존재하지 않는 가계부입니다.", "ET01"),

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
