package com.fittrack.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "readiness_scores", uniqueConstraints = @UniqueConstraint(name = "uk_readiness_user_date", columnNames = {"user_id", "score_date"}))
public class ReadinessScore extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private LocalDate scoreDate;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private String recommendation;
}
