package com.project.abook.global.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "공통 API 응답 DTO")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    @Schema(description = "HTTP 상태 코드", examples = "200")
    @JsonProperty("status_code")
    private Integer statusCode;

    @Schema(description = "응답 메시지", examples = "요청이 성공했습니다.")
    @JsonProperty("message")
    private String message;

    @Schema(description = "응답 데이터")
    @JsonProperty("data")
    private T data;

    @Schema(description = "요청 성공 여부", example = "true")
    @JsonProperty("success")
    private boolean success;

    /**
     * 정형화된 응답은 팩토리
     * 커스텀이 필요할 경우 빌더 직접 사용
     */

    // 성공 응답 팩토리
    public static <T> ApiResponse<T> ok(T data) {
        return ApiResponse.<T>builder()
                .statusCode(200)
                .message("요청이 성공했습니다.")
                .data(data)
                .success(true)
                .build();
    }

    public static <T> ApiResponse<T> ok(String message, T data) {
        return ApiResponse.<T>builder()
                .statusCode(200)
                .message(message)
                .data(data)
                .success(true)
                .build();
    }

    // 실패 응답 팩토리
    public static <T> ApiResponse<T> error(String message, int statusCode) {
        return ApiResponse.<T>builder()
                .statusCode(statusCode)
                .message(message)
                .success(false)
                .build();
    }
}
