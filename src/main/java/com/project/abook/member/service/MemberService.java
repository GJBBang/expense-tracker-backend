package com.project.abook.member.service;

import com.project.abook.member.domain.Member;
import com.project.abook.member.dto.MemberRegisterRequest;

public interface MemberService {

    Member save(MemberRegisterRequest request);
}
