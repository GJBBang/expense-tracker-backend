package com.project.abook.extracker.domain;

import com.project.abook.global.domain.BaseEntity;
import com.project.abook.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
public class ExTracker extends BaseEntity {

    @Schema(defaultValue = "1")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "extracker_id")
    private Long id;

    private LocalDate date;              // 날짜 (지출/수입 날짜)

    private String category;             // 카테고리 (예: 식비, 교통, 월급 등)

    private String description;          // 설명

    private Long amount;                 // 금액

    @Enumerated(EnumType.STRING)
    private EntryType type;              // 수입/지출 구분

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;    // 결제 수단

    private String memo;                 // 추가 메모

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memeber_id", nullable = false)
    private Member member;

    public void setMember(Member member) {
        this.member = member;
    }
}
