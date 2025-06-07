package com.project.abook.auth.controller;

import com.project.abook.auth.dto.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/v1")
public class AuthRestController {

    @PostMapping("/members/login")
    public ResponseEntity<Void> login(LoginRequest request) {

        log.info("request Name : {}", request.getMemberName());
        log.info("request Password : {}", request.getPassword());

        return ResponseEntity.noContent().build();
    }
}
