package com.project.abook.global.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private String errorCode;
    private String errorMessage;

    public static ErrorResponse from(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getCode(), errorCode.getMessage());
    }

    public static String toJson(JwtException e) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.getFactory().configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), true);
        return objectMapper.writeValueAsString(new ErrorResponse(e.getErrorCode().getCode(), e.getErrorCode().getMessage()));
    }

    public static String toJson(ErrorCode error) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.getFactory().configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), true);
        return objectMapper.writeValueAsString(new ErrorResponse(error.getCode(), error.getMessage()));
    }
}
