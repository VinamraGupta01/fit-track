package com.fittrack.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

public class DashboardDtos {
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class SummaryResponse {
        private long totalWorkouts;
        private long weeklyWorkouts;
        private long monthlyWorkouts;
        private int averageRecoveryScore;
        private int latestReadinessScore;
        private String latestRecommendation;
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class TrendPoint {
        private LocalDate date;
        private Integer workouts;
        private Integer recoveryScore;
        private Integer readinessScore;
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class TrendsResponse {
        private List<TrendPoint> points;
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class PlatformStatsResponse {
        private long users;
        private long workouts;
        private long recoveryLogs;
        private double avgReadiness;
    }
}
