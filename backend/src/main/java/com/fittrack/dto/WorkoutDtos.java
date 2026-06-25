package com.fittrack.dto;

import com.fittrack.enums.Intensity;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WorkoutDtos {
    @Getter @Setter
    public static class WorkoutRequest {
        @NotBlank @Size(max = 120)
        private String title;
        private Long categoryId;
        @NotNull
        private Intensity intensity;
        @NotNull @Min(1) @Max(600)
        private Integer durationMinutes;
        @NotNull @Min(0) @Max(5000)
        private Integer caloriesBurned;
        @NotNull
        private LocalDate workoutDate;
        @Size(max = 1000)
        private String notes;
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class WorkoutResponse {
        private Long id;
        private String title;
        private Long categoryId;
        private String categoryName;
        private Intensity intensity;
        private Integer durationMinutes;
        private Integer caloriesBurned;
        private LocalDate workoutDate;
        private String notes;
        private LocalDateTime createdAt;
    }

    @Getter @Setter
    public static class CategoryRequest {
        @NotBlank @Size(max = 80)
        private String name;
        @Size(max = 255)
        private String description;
        private boolean active = true;
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CategoryResponse {
        private Long id;
        private String name;
        private String description;
        private boolean active;
    }
}
