package com.project.abook.member.controller;

import com.project.abook.member.dto.MemberRegisterRequest;
import com.project.abook.member.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberRestController {

    private final MemberServiceImpl memberService;

    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestBody MemberRegisterRequest request) {
        memberService.save(request);
        return ResponseEntity.noContent().build();
    }
}
