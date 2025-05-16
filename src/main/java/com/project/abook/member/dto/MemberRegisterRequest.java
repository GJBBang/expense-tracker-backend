package com.project.abook.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberRegisterRequest {

    private String userName;
    private String password;
    private String email;

    @Builder
    public MemberRegisterRequest(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
}
