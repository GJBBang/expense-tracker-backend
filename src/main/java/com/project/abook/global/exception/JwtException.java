package com.project.abook.global.exception;

public class JwtException extends CustomException {

    public JwtException(ErrorCode errorcode) {
        super(errorcode);
    }
}
