package com.project.abook.auth.domain;

import com.project.abook.global.exception.BusinessException;
import com.project.abook.global.exception.ErrorCode;

import java.util.Arrays;

public enum Authority {

    ROLE_MEMBER("일반 사용자", "A01"),
    ROLE_ADMIN("시스템 관리자", "A99"),
    ;

    private final String role;
    private final String authorityCode;

    Authority(String role, String authorityCode) {
        this.role = role;
        this.authorityCode = authorityCode;
    }

    public String getRole() {
        return role;
    }

    public String getAuthorityCode() {
        return authorityCode;
    }

    public static Authority convertCodeToAuthority(String inputAuthorityCode) {
        return Arrays.stream(values())
                .filter(authority -> authority.authorityCode.equals(inputAuthorityCode))
                .findFirst()
                .orElseThrow(() -> new BusinessException(ErrorCode.AUTHORITY_NOT_FOUND_BY_AUTHORITY_CODE));
    }
}
