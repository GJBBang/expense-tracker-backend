package com.project.abook.auth.controller;

import com.project.abook.auth.dto.request.LoginRequest;
import com.project.abook.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthRestController {

    private final AuthService authService;

    @PostMapping("/members/login")
    public ResponseEntity<Void> login(LoginRequest request) {

        log.debug("request Id : {}", request.getUserId());
        log.debug("request Password : {}", request.getPassword());

        authService.login(request);

        return ResponseEntity.noContent().build();
    }
}
