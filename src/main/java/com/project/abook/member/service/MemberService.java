package com.project.abook.member.service;

import com.project.abook.auth.service.AuthService;
import com.project.abook.global.exception.BusinessException;
import com.project.abook.global.exception.ErrorCode;
import com.project.abook.global.util.ServiceUtils;
import com.project.abook.member.domain.Member;
import com.project.abook.member.dto.MemberRegisterRequest;
import com.project.abook.member.mapper.MemberMapper;
import com.project.abook.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;

    private final AuthService authService;

    private final MemberMapper memberMapper;

    private final MemberRepository memberRepository;



    public Long save(MemberRegisterRequest request) {

        // member 저장 전 검증
        checkDuplicateMemberName(request.getMemberName());


        // 저장
        Member member = memberMapper.toMember(request);
        member.encryptPassword(passwordEncoder);

        // 저장 후 자동 로그인 처리
        authService.login(memberMapper.toLoginRequest(request));

        return memberRepository.save(member).getId();
    }

    public void checkDuplicateMemberName(String memberName) {
        ServiceUtils.throwIfExists(() -> memberRepository.existsByMemberName(memberName),
                ErrorCode.MEMBER_USERNAME_DUPLICATED);
    }

    @Transactional(readOnly = true)
    public Member findByMemberName(String memberName) {
        return memberRepository.findByMemberName(memberName)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND_BY_MEMBER_NAME));
    }
}
