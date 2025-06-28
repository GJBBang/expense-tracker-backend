package com.project.abook.member.service;

import com.project.abook.auth.domain.RefreshToken;
import com.project.abook.auth.dto.response.LoginResponse;
import com.project.abook.auth.repository.RefreshTokenRepository;
import com.project.abook.global.exception.BusinessException;
import com.project.abook.global.exception.ErrorCode;
import com.project.abook.global.util.ServiceUtils;
import com.project.abook.member.domain.Member;
import com.project.abook.member.dto.event.MemberRegisterEvent;
import com.project.abook.member.dto.request.MemberRegisterRequest;
import com.project.abook.member.mapper.MemberMapper;
import com.project.abook.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;

    private final MemberMapper memberMapper;

    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;



    public LoginResponse save(MemberRegisterRequest request) {

        // member 저장 전 검증
        checkDuplicateUserName(request.getUserName());

        // 저장
        Member member = memberMapper.toMember(request);
        member.encryptPassword(passwordEncoder);
        memberRepository.save(member);

        // 자동 로그인 이벤트 처리 (순환 참조로 인한 이벤트리스너 처리)
        CompletableFuture<String> accessTokenFuture = new CompletableFuture<>();
        eventPublisher.publishEvent(MemberRegisterEvent.builder()
                .userId(request.getUserId())
                .password(request.getPassword())
                .accessTokenFuture(accessTokenFuture)
                .build()
        );

        try {
            String accessToken = accessTokenFuture.get(); // 블로킹 호출
            return LoginResponse.builder()
                    .userId(member.getUserId())
                    .token(accessToken)
                    .build();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("자동 로그인 처리 중 오류 발생", e);
        }
    }

    public void checkDuplicateUserName(String userName) {
        ServiceUtils.throwIfExists(() -> memberRepository.existsByUserName(userName),
                ErrorCode.MEMBER_USERNAME_DUPLICATED);
    }

    @Transactional(readOnly = true)
    public Member findByUserId(String userId) {
        return memberRepository.findByUserId(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND_BY_MEMBER_NAME));
    }

    @Transactional(readOnly = true)
    public Member findByUserName(String userName) {
        return memberRepository.findByUserName(userName)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND_BY_MEMBER_NAME));
    }
}
