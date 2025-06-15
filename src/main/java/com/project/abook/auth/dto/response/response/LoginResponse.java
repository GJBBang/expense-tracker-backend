package com.project.abook.auth.dto.response.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponse {

    private String userId;
    private String token;

    @Builder
    public LoginResponse(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
