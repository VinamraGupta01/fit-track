package com.fittrack.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "recovery_logs", uniqueConstraints = @UniqueConstraint(name = "uk_recovery_user_date", columnNames = {"user_id", "log_date"}))
public class RecoveryLog extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private LocalDate logDate;

    @Column(nullable = false)
    private Double sleepHours;

    @Column(nullable = false)
    private Integer sleepQuality;

    @Column(nullable = false)
    private Integer stressLevel;

    @Column(nullable = false)
    private Integer muscleSoreness;

    @Column(nullable = false)
    private Integer recoveryScore;
}
