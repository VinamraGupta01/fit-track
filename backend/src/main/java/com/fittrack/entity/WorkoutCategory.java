package com.fittrack.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "workout_categories")
public class WorkoutCategory extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    private String description;
    @Column(nullable = false)
    private boolean active;
}
