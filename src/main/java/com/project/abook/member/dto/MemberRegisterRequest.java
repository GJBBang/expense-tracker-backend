package com.project.abook.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberRegisterRequest {

    private String memberName;
    private String password;
    private String email;

    @Builder
    public MemberRegisterRequest(String memberName, String password, String email) {
        this.memberName = memberName;
        this.password = password;
        this.email = email;
    }
}
