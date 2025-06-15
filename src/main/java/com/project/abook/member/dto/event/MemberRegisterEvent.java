package com.project.abook.member.dto.event;


import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberRegisterEvent {

    private String userId;
    private String password;

    @Builder
    public MemberRegisterEvent(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
