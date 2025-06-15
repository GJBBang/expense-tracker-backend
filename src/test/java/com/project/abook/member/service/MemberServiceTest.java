package com.project.abook.member.service;

import com.project.abook.member.domain.Member;
import com.project.abook.member.dto.MemberRegisterRequest;
import com.project.abook.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    @DisplayName("정상적인 회원 정보로 회원가입에 성공한다.")
    void 회원저장() throws Exception {
        // given
        MemberRegisterRequest memberRegisterRequest = MemberRegisterRequest.builder()
                .userId("aaaa")
                .userName("aUser")
                .password("1111")
                .email("abc@abc.aaa")
                .build();

        // when
        Long memberId = memberService.save(memberRegisterRequest);
        Member saveMember = memberRepository.findById(memberId).get();

        // then
        assertEquals("aUser", saveMember.getUserName());
    }
}