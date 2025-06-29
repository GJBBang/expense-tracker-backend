package com.project.abook.auth.service;

import com.project.abook.auth.domain.RefreshToken;
import com.project.abook.auth.dto.request.LoginRequest;
import com.project.abook.auth.dto.response.LoginResponse;
import com.project.abook.auth.dto.response.TokenResponse;
import com.project.abook.auth.infrastructure.JwtTokenProvider;
import com.project.abook.auth.mapper.AuthMapper;
import com.project.abook.auth.repository.RefreshTokenRepository;
import com.project.abook.member.domain.Member;
import com.project.abook.member.dto.event.MemberRegisterEvent;
import com.project.abook.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
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

    private final AuthMapper authMapper;


    public LoginResponse login(LoginRequest request) {
        Member member = memberService.findByUserId(request.getUserId());
        member.checkPassword(passwordEncoder, member.getPassword());

        TokenResponse tokenResponse = jwtTokenProvider.createToken(member.getUserId(), member.getAuthority());

        String refreshToken = saveRefreshToken(member, tokenResponse);
        log.debug("Refresh token: {}", refreshToken);

        return LoginResponse.builder()
                .userId(member.getUserId())
                .token(tokenResponse.getAccessToken())
                .build();
    }

    public String saveRefreshToken(Member member, TokenResponse tokenResponse) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(member.getUserId())
                .orElse(RefreshToken.builder()
                        .refreshToken(tokenResponse.getRefreshToken())
                        .userId(member.getUserId())
                        .userName(member.getUserName())
                        .createdAt(LocalDateTime.now())
                        .expireAt(LocalDateTime.now().plusDays(15))
                        .build()
                );
        refreshTokenRepository.save(refreshToken);

        return refreshToken.getRefreshToken();
    }

    @EventListener
    @Async
    public void handleMemberRegisterEvent(MemberRegisterEvent event) {
        LoginRequest loginRequest = authMapper.toLoginRequest(event);
        LoginResponse loginResponse = login(loginRequest);
        try {
            event.getAccessTokenFuture().complete(loginResponse.getToken()); // 토큰 설정
        } catch (Exception e) {
            event.getAccessTokenFuture().completeExceptionally(e); // 예외 발생 시
        }
    }
}
