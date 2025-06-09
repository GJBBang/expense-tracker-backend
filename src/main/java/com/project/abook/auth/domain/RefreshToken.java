package com.project.abook.auth.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {

    @Id @GeneratedValue
    private Long id;

    private String refreshToken;

    private String memeberName;

    private LocalDateTime createdAt;

    private LocalDateTime expireAt;

    @Builder
    public RefreshToken(String refreshToken, String memeberName, LocalDateTime createdAt, LocalDateTime expireAt) {
        this.refreshToken = refreshToken;
        this.memeberName = memeberName;
        this.createdAt = createdAt;
        this.expireAt = expireAt;
    }
}
