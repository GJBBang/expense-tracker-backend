package com.project.abook.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
public class AuthRestController {

    @PostMapping("/members/login")
    public ResponseEntity<Void> login() {
        return ResponseEntity.noContent().build();
    }
}
