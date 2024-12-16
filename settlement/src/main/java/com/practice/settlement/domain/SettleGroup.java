package com.practice.settlement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@ToString
@Table(name = "settlegroup")
public class SettleGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private Long serviceId;

    private Long totalCount;

    private Long totalFee;

    private LocalDateTime createdAt;

    public SettleGroup(Long customerId, Long serviceId, Long totalCount, Long totalFee) {
        this.customerId = customerId;
        this.serviceId = serviceId;
        this.totalCount = totalCount;
        this.totalFee = totalFee;
        this.createdAt = LocalDateTime.now();
    }

}
