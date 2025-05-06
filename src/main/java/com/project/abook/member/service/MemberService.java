package com.project.abook.member.service;

import com.project.abook.member.dto.MemberRegisterRequest;

public interface MemberService {

    Long save(MemberRegisterRequest request);
}
