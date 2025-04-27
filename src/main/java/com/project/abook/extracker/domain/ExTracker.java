package com.project.abook.extracker.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExTracker {

    @Schema(defaultValue = "1")
    private Long id;

    @Schema(defaultValue = "default title")
    private String title;

    @Schema(defaultValue = "100000")
    private BigDecimal price;
}
