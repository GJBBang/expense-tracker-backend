package com.project.abook.member.service;

import com.project.abook.member.domain.Member;
import com.project.abook.member.dto.MemberRegisterRequest;
import com.project.abook.member.mapper.MemberMapper;
import com.project.abook.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public Long save(MemberRegisterRequest request) {
        return memberRepository.save(memberMapper.toMember(request)).getId();
    }
}
