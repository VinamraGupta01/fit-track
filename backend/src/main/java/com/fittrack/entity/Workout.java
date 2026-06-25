package com.fittrack.entity;

import com.fittrack.enums.Intensity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "workouts", indexes = @Index(name = "idx_workout_user_date", columnList = "user_id, workout_date"))
public class Workout extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private WorkoutCategory category;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Intensity intensity;

    @Column(nullable = false)
    private Integer durationMinutes;

    @Column(nullable = false)
    private Integer caloriesBurned;

    @Column(nullable = false)
    private LocalDate workoutDate;

    @Column(columnDefinition = "TEXT")
    private String notes;
}
