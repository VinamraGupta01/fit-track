package com.fittrack.service;

import com.fittrack.dto.RecoveryDtos.ReadinessResponse;
import com.fittrack.entity.ReadinessScore;
import com.fittrack.entity.RecoveryLog;
import com.fittrack.entity.User;
import com.fittrack.repository.ReadinessScoreRepository;
import com.fittrack.repository.RecoveryLogRepository;
import com.fittrack.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReadinessService {
    private final ReadinessScoreRepository readinessScoreRepository;
    private final RecoveryLogRepository recoveryLogRepository;
    private final WorkoutRepository workoutRepository;
    private final CurrentUserService currentUserService;

    public ReadinessResponse today() { return map(generateFor(currentUserService.getCurrentUser(), LocalDate.now())); }

    public ReadinessScore generateFor(User user, LocalDate date) {
        RecoveryLog recovery = recoveryLogRepository.findByUserAndLogDate(user, date)
                .orElseGet(() -> recoveryLogRepository.findTopByUserOrderByLogDateDesc(user).orElse(null));
        int recoveryScore = recovery == null ? 70 : recovery.getRecoveryScore();
        int weeklyMinutes = workoutRepository.totalMinutes(user, date.minusDays(6), date);
        int trainingPenalty = Math.min(20, weeklyMinutes / 60 * 2);
        int score = Math.max(0, Math.min(100, recoveryScore - trainingPenalty + 5));
        String recommendation = recommendation(score);
        ReadinessScore entity = readinessScoreRepository.findByUserAndScoreDate(user, date)
                .orElseGet(() -> ReadinessScore.builder().user(user).scoreDate(date).build());
        entity.setScore(score);
        entity.setRecommendation(recommendation);
        return readinessScoreRepository.save(entity);
    }

    private String recommendation(int score) {
        if (score >= 75) return "Ready for Intense Training";
        if (score >= 50) return "Moderate Training Recommended";
        return "Recovery Day Recommended";
    }

    public ReadinessResponse map(ReadinessScore score) {
        return ReadinessResponse.builder().scoreDate(score.getScoreDate()).score(score.getScore()).recommendation(score.getRecommendation()).build();
    }
}
