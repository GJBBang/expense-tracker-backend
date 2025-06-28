package com.project.abook.member.controller;

import com.project.abook.auth.dto.response.LoginResponse;
import com.project.abook.global.dto.ApiResponse;
import com.project.abook.member.dto.request.MemberRegisterRequest;
import com.project.abook.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberRestController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity join(@RequestBody MemberRegisterRequest request) {
        LoginResponse loginResponse = memberService.save(request);
        return ResponseEntity.ok(ApiResponse.ok("회원가입 성공", loginResponse));
    }
}
