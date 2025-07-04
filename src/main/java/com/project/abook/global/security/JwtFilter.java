package com.project.abook.global.security;

import com.project.abook.auth.infrastructure.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class JwtFilter extends OncePerRequestFilter {

    private static final Pattern BEARER_SCHEMA = Pattern.compile("^Bearer$", Pattern.CASE_INSENSITIVE);

    private final JwtTokenProvider jwtTokenProvider;

    public JwtFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        resolveHeader(request).ifPresent(this::saveAuthentication);
        filterChain.doFilter(request, response);
    }

    private Optional<String> resolveHeader(HttpServletRequest request) {
        String tokenInfo = request.getHeader(AUTHORIZATION);
        if (Strings.isEmpty(tokenInfo)) {
            return Optional.empty();
        }
        return getToken(tokenInfo.split(" "));
    }

    private Optional<String> getToken(String[] tokenInfo) {
        if (tokenInfo.length == 2 && BEARER_SCHEMA.matcher(tokenInfo[0]).matches()) {
            return Optional.of(tokenInfo[1]);
        }
        return Optional.empty();
    }

    private void saveAuthentication(String token) {
        Authentication authentication = jwtTokenProvider.resolveAccessToken(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
