package com.project.abook.auth.service;

import com.project.abook.auth.domain.RefreshToken;
import com.project.abook.auth.dto.request.LoginRequest;
import com.project.abook.auth.dto.response.response.LoginResponse;
import com.project.abook.auth.dto.response.response.TokenResponse;
import com.project.abook.auth.infrastructure.JwtTokenProvider;
import com.project.abook.auth.repository.RefreshTokenRepository;
import com.project.abook.member.domain.Member;
import com.project.abook.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    private final MemberService memberService;

    private final RefreshTokenRepository refreshTokenRepository;


    public LoginResponse login(LoginRequest request) {
        Member member = memberService.findByUserName(request.getUserName());
        member.checkPassword(passwordEncoder, member.getPassword());

        TokenResponse tokenResponse = jwtTokenProvider.createToken(member.getUserName(), member.getAuthority());
        log.debug("access token: {}", tokenResponse.getAccessToken());

        String refreshToken = saveRefreshToken(member, tokenResponse);
        log.debug("refresh token: {}", refreshToken);

        return LoginResponse.builder()
                .userId(member.getUserId())
                .token(refreshToken)
                .build();
    }

    public String saveRefreshToken(Member member, TokenResponse tokenResponse) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserName(member.getUserName())
                .orElse(RefreshToken.builder()
                        .refreshToken(tokenResponse.getRefreshToken())
                        .memeberName(member.getUserName())
                        .createdAt(LocalDateTime.now())
                        .expireAt(LocalDateTime.now().plusDays(15))
                        .build()
                );
        refreshTokenRepository.save(refreshToken);

        return refreshToken.getRefreshToken();
    }
}
