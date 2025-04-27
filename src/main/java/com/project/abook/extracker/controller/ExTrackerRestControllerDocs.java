package com.project.abook.extracker.controller;

import com.project.abook.extracker.domain.ExTracker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "ExTracker TEST", description = "TEST CRUD")
public interface ExTrackerRestControllerDocs {

    @Operation(summary = "해당 일자 가계부 정보", description = "해당 일자의 가계부 정보를 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ExTracker.class)
                    )),
            @ApiResponse(responseCode = "500", description = "서버에러 발생", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<String> getExTracker(
            @Parameter(name = "id", description = "가계부 id", required = true, example = "1")
            @PathVariable Long id
    );

    public ResponseEntity<Void> saveExTracker(ExTracker exTracker);
}
