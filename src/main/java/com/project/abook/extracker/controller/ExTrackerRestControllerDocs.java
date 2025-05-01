package com.project.abook.extracker.controller;

import com.project.abook.extracker.domain.ExTracker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "ExTracker TEST", description = "TEST CRUD")
public interface ExTrackerRestControllerDocs {

    @Operation(summary = "해당 일자 가계부 정보", description = "해당 일자의 가계부 정보를 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ExTracker.class)
                    )),
            @ApiResponse(responseCode = "500", description = "서버에러 발생",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "ServerErrorExample",
                                    summary = "서버 에러",
                                    description = "서버 내부 오류 발생 시 반환되는 메시지 형식",
                                    value = "{ \"timestamp\": \"2025-05-01T10:00:00\", \"status\": 500, \"error\": \"Internal Server Error\", \"message\": \"예기치 못한 오류가 발생했습니다.\", \"path\": \"/api/v1/exTracker/1\" }"
                            )
                    ))
    })
    public ResponseEntity<String> getExTracker(
            @Parameter(name = "id", description = "가계부 id", required = true, example = "1")
            @PathVariable Long id
    );

    @Operation(summary = "해당 일자 가계부 정보 저장", description = "해당 일자의 가계부 정보를 저장합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "",
                                    summary = "성공",
                                    description = "가계부 저장 성공",
                                    value = "{ \"sucessYN\": \"Y\", \"message\": \"가계부 저장 성공\" }"
                            )
                    )),
            @ApiResponse(responseCode = "500", description = "서버에러 발생",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "ServerErrorExample",
                                    summary = "서버 에러",
                                    description = "서버 내부 오류 발생 시 반환되는 메시지 형식",
                                    value = "{ \"timestamp\": \"2025-05-01T10:00:00\", \"status\": 500, \"error\": \"Internal Server Error\", \"message\": \"예기치 못한 오류가 발생했습니다.\", \"path\": \"/api/v1/exTracker/save\" }"
                            )
                    ))
    })
    public ResponseEntity<Void> saveExTracker(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "생성할 가계부 정보", required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "default Object",
                                    summary = "",
                                    description = "",
                                    value = "{ \"id\": 1, \"title\": \"title ex\", \"price\": 3000 }"
                            )
                    )
            ) ExTracker exTracker
    );
}
