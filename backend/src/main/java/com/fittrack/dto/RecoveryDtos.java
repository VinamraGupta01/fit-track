package com.fittrack.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

public class RecoveryDtos {
    @Getter @Setter
    public static class RecoveryRequest {
        private LocalDate logDate;
        @NotNull @DecimalMin("0.0") @DecimalMax("14.0")
        private Double sleepHours;
        @NotNull @Min(1) @Max(10)
        private Integer sleepQuality;
        @NotNull @Min(1) @Max(10)
        private Integer stressLevel;
        @NotNull @Min(1) @Max(10)
        private Integer muscleSoreness;
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class RecoveryResponse {
        private Long id;
        private LocalDate logDate;
        private Double sleepHours;
        private Integer sleepQuality;
        private Integer stressLevel;
        private Integer muscleSoreness;
        private Integer recoveryScore;
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ReadinessResponse {
        private LocalDate scoreDate;
        private Integer score;
        private String recommendation;
    }
}
