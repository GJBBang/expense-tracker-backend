package com.project.abook.member.service;

import com.project.abook.global.exception.BusinessException;
import com.project.abook.global.exception.ErrorCode;
import com.project.abook.global.util.ServiceUtils;
import com.project.abook.member.domain.Member;
import com.project.abook.member.dto.event.MemberRegisterEvent;
import com.project.abook.member.dto.request.MemberRegisterRequest;
import com.project.abook.member.mapper.MemberMapper;
import com.project.abook.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;

    private final MemberMapper memberMapper;

    private final MemberRepository memberRepository;



    public Long save(MemberRegisterRequest request) {

        // member 저장 전 검증
        checkDuplicateUserName(request.getUserName());


        // 저장
        Member member = memberMapper.toMember(request);
        member.encryptPassword(passwordEncoder);
//        memberRepository.save(member);

        // 저장 후 자동 로그인 처리
        eventPublisher.publishEvent(MemberRegisterEvent.builder()
                .userId(request.getUserId())
                .password(request.getPassword())
                .build()
        );

        return 1L;
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
