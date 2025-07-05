package com.project.abook.extracker.dto.response;

import com.project.abook.extracker.domain.EntryType;
import com.project.abook.extracker.domain.ExTracker;
import com.project.abook.extracker.domain.PaymentMethod;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ExTrackerDetailResponse {

    private Long id;
    private LocalDate date;
    private String category;
    private String description;
    private Long amount;
    private EntryType type;
    private PaymentMethod paymentMethod;
    private String memo;
    private Long memberId;

    @Builder
    public ExTrackerDetailResponse(ExTracker exTracker) {
        this.id = exTracker.getId();
        this.date = exTracker.getDate();
        this.category = exTracker.getCategory();
        this.description = exTracker.getDescription();
        this.amount = exTracker.getAmount();
        this.type = exTracker.getType();
        this.paymentMethod = exTracker.getPaymentMethod();
        this.memo = exTracker.getMemo();
        this.memberId = exTracker.getMember().getId();
    }
}
