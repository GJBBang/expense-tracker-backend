package com.project.abook.member.service;

import com.project.abook.member.domain.Member;
import com.project.abook.member.dto.MemberRegisterRequest;
import com.project.abook.member.mapper.MemberMapper;
import com.project.abook.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public Member save(MemberRegisterRequest request) {
        return memberRepository.save(memberMapper.toMember(request));
    }
}
