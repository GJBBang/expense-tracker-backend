package com.project.abook.auth.infrastructure;

import com.project.abook.auth.domain.Authority;
import com.project.abook.auth.dto.response.response.TokenResponse;
import com.project.abook.global.exception.ErrorCode;
import com.project.abook.global.exception.JwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

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

    public Authentication resolveAccessToken(String accessToken) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(accessToken).getBody();

            Collection<? extends GrantedAuthority> authorities =
                    Arrays.stream(claims.get(JWT_PAYLOAD_AUTHORITY_TYPE).toString().split(","))
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());
            return new UsernamePasswordAuthenticationToken(claims.getSubject(), accessToken, authorities);
        } catch (ExpiredJwtException e) {
            throw new JwtException(ErrorCode.INVALID_EXPIRED_JWT);
        } catch (SecurityException | MalformedJwtException e) {
            throw new JwtException(ErrorCode.INVALID_MALFORMED_JWT);
        } catch (UnsupportedJwtException e) {
            throw new JwtException(ErrorCode.INVALID_UNSUPPORTED_JWT);
        } catch (IllegalArgumentException | SignatureException e) {
            throw new JwtException(ErrorCode.INVALID_ILLEGAL_ARGUMENT_JWT);
        }
    }
}
