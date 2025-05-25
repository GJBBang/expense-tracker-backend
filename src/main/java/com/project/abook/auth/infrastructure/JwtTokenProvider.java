package com.project.abook.auth.infrastructure;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private final String headerType;
    private final String issuer;
    private final Long accessTime;
    private final Long refreshTime;

    public JwtTokenProvider(
            @Value("${jwt.token.header-type}") String headerType,
            @Value("${jwt.token.issuer") String issuer,
            @Value("${jwt.token.access-time") Long accessTime,
            @Value("${jwt.token.refesh-time") Long refeshTime
    ) {
        this.headerType = headerType;
        this.issuer = issuer;
        this.accessTime = accessTime;
        this.refreshTime = refeshTime;
    }

//    public String generateAccessToken(String subject) {
//        return Jwts.builder()
//                .
//    }
}
