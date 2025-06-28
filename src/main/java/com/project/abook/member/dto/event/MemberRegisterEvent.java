package com.project.abook.member.dto.event;


import lombok.Builder;
import lombok.Getter;

import java.util.concurrent.CompletableFuture;

@Getter
public class MemberRegisterEvent {

    private final String userId;
    private final String password;
    private final CompletableFuture<String> accessTokenFuture;

    @Builder
    public MemberRegisterEvent(String userId, String password, CompletableFuture<String> accessTokenFuture) {
        this.userId = userId;
        this.password = password;
        this.accessTokenFuture = accessTokenFuture;
    }
}
