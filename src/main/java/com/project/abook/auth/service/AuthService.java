package com.project.abook.auth.service;

import com.project.abook.auth.dto.request.LoginRequest;
import com.project.abook.auth.dto.response.TokenResponse;
import com.project.abook.auth.infrastructure.JwtTokenProvider;
import com.project.abook.global.exception.BusinessException;
import com.project.abook.global.exception.ErrorCode;
import com.project.abook.member.domain.Member;
import com.project.abook.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public void login(LoginRequest request) {
        Member member = memberService.findByMemberName(request.getMemberName());
        member.checkPassword(passwordEncoder, member.getPassword());

        TokenResponse tokenResponse = jwtTokenProvider.createToken(member.getMemberName(), member.getAuthority());
        log.debug("access token: {}", tokenResponse.getAccessToken());
        log.debug("refresh token: {}", tokenResponse.getRefreshToken());

        throw new BusinessException(ErrorCode.GLOBAL_INTERNAL_SERVER_ERROR);
    }
}
