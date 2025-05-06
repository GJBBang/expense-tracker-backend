package com.project.abook.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberRegisterRequest {

    private String username;
    private String password;
    private String email;

    @Builder
    public MemberRegisterRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
