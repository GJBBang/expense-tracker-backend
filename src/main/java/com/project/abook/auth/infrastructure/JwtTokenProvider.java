package com.project.abook.auth.infrastructure;

import com.project.abook.auth.domain.Authority;
import com.project.abook.auth.dto.TokenResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final String JWT_HEADER_PARAM_TYPE = "typ";
    private static final String JWT_PAYLOAD_AUTHORITY_TYPE = "auth";

    private final Key key;
    private final String headerType;
    private final String issuer;
    private final long accessTime;
    private final long refreshTime;

    public JwtTokenProvider(@Value("${jwt.token.header-type}") String headerType,
                            @Value("${jwt.token.issuer}") String issuer,
                            @Value("${jwt.token.secret}") String secret,
                            @Value("${jwt.token.access-time}") long accessTime,
                            @Value("${jwt.token.refresh-time}") long refreshTime) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.headerType = headerType;
        this.issuer = issuer;
        this.accessTime = accessTime;
        this.refreshTime = refreshTime;
    }

    public TokenResponse createToken(String subject, Authority authority) {
        String accessToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS512)
                .setHeaderParam(JWT_HEADER_PARAM_TYPE, headerType)
                .setSubject(subject)
                .claim(JWT_PAYLOAD_AUTHORITY_TYPE, authority.getAuthorityCode())
                .setIssuer(issuer)
                .setExpiration(new Date((new Date()).getTime() + accessTime))
                .setIssuedAt(new Date())
                .compact();

        String refreshToken = Jwts.builder()
                .setExpiration(new Date(new Date().getTime() + refreshTime))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
