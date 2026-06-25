package com.fittrack.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "workout_sessions")
public class WorkoutSession extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workout_id")
    private Workout workout;

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private Integer perceivedExertion;
    @Column(columnDefinition = "TEXT")
    private String notes;
}
