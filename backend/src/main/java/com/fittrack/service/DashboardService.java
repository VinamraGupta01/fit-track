package com.fittrack.service;

import com.fittrack.dto.DashboardDtos.*;
import com.fittrack.entity.User;
import com.fittrack.repository.ReadinessScoreRepository;
import com.fittrack.repository.RecoveryLogRepository;
import com.fittrack.repository.UserRepository;
import com.fittrack.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final CurrentUserService currentUserService;
    private final WorkoutRepository workoutRepository;
    private final RecoveryLogRepository recoveryLogRepository;
    private final ReadinessScoreRepository readinessScoreRepository;
    private final UserRepository userRepository;

    public SummaryResponse summary() {
        User user = currentUserService.getCurrentUser();
        LocalDate today = LocalDate.now();
        var latestRecovery = recoveryLogRepository.findTopByUserOrderByLogDateDesc(user);
        var latestReadiness = readinessScoreRepository.findTopByUserOrderByScoreDateDesc(user);
        return SummaryResponse.builder()
                .totalWorkouts(workoutRepository.countByUser(user))
                .weeklyWorkouts(workoutRepository.countByUserAndWorkoutDateBetween(user, today.minusDays(6), today))
                .monthlyWorkouts(workoutRepository.countByUserAndWorkoutDateBetween(user, today.withDayOfMonth(1), today))
                .averageRecoveryScore(latestRecovery.map(r -> r.getRecoveryScore()).orElse(0))
                .latestReadinessScore(latestReadiness.map(r -> r.getScore()).orElse(0))
                .latestRecommendation(latestReadiness.map(r -> r.getRecommendation()).orElse("Add recovery log to generate readiness"))
                .build();
    }

    public TrendsResponse trends() {
        User user = currentUserService.getCurrentUser();
        LocalDate start = LocalDate.now().minusDays(13);
        LocalDate end = LocalDate.now();
        var workouts = workoutRepository.findByUserAndWorkoutDateBetween(user, start, end);
        var recoveries = recoveryLogRepository.findByUserAndLogDateBetweenOrderByLogDateAsc(user, start, end);
        var readiness = readinessScoreRepository.findByUserAndScoreDateBetweenOrderByScoreDateAsc(user, start, end);
        var points = new ArrayList<TrendPoint>();
        for (int i=0; i<14; i++) {
            LocalDate d = start.plusDays(i);
            int workoutCount = (int) workouts.stream().filter(w -> w.getWorkoutDate().equals(d)).count();
            Integer recovery = recoveries.stream().filter(r -> r.getLogDate().equals(d)).findFirst().map(r -> r.getRecoveryScore()).orElse(null);
            Integer ready = readiness.stream().filter(r -> r.getScoreDate().equals(d)).findFirst().map(r -> r.getScore()).orElse(null);
            points.add(TrendPoint.builder().date(d).workouts(workoutCount).recoveryScore(recovery).readinessScore(ready).build());
        }
        return TrendsResponse.builder().points(points).build();
    }

    public PlatformStatsResponse platformStats() {
        return PlatformStatsResponse.builder()
                .users(userRepository.count())
                .workouts(workoutRepository.count())
                .recoveryLogs(recoveryLogRepository.count())
                .avgReadiness(readinessScoreRepository.averageScore())
                .build();
    }
}
