package com.fittrack.service;

import com.fittrack.dto.RecoveryDtos.*;
import com.fittrack.entity.RecoveryLog;
import com.fittrack.entity.User;
import com.fittrack.repository.RecoveryLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecoveryService {
    private final RecoveryLogRepository recoveryLogRepository;
    private final ReadinessService readinessService;
    private final CurrentUserService currentUserService;

    public RecoveryResponse upsert(RecoveryRequest request) {
        User user = currentUserService.getCurrentUser();
        LocalDate date = request.getLogDate() == null ? LocalDate.now() : request.getLogDate();
        RecoveryLog log = recoveryLogRepository.findByUserAndLogDate(user, date).orElseGet(() -> RecoveryLog.builder().user(user).logDate(date).build());
        log.setSleepHours(request.getSleepHours());
        log.setSleepQuality(request.getSleepQuality());
        log.setStressLevel(request.getStressLevel());
        log.setMuscleSoreness(request.getMuscleSoreness());
        log.setRecoveryScore(calculateRecoveryScore(request));
        RecoveryLog saved = recoveryLogRepository.save(log);
        readinessService.generateFor(user, date);
        return map(saved);
    }

    public List<RecoveryResponse> listLast30Days() {
        User user = currentUserService.getCurrentUser();
        return recoveryLogRepository.findByUserAndLogDateBetweenOrderByLogDateAsc(user, LocalDate.now().minusDays(29), LocalDate.now()).stream().map(this::map).toList();
    }

    public int calculateRecoveryScore(RecoveryRequest request) {
        double sleepScore = Math.min(request.getSleepHours() / 8.0, 1.0) * 35;
        double qualityScore = request.getSleepQuality() * 3.0;
        double stressPenalty = (10 - request.getStressLevel()) * 2.0;
        double sorenessPenalty = (10 - request.getMuscleSoreness()) * 1.5;
        return Math.max(0, Math.min(100, (int)Math.round(sleepScore + qualityScore + stressPenalty + sorenessPenalty)));
    }

    public RecoveryResponse map(RecoveryLog log) {
        return RecoveryResponse.builder().id(log.getId()).logDate(log.getLogDate()).sleepHours(log.getSleepHours())
                .sleepQuality(log.getSleepQuality()).stressLevel(log.getStressLevel()).muscleSoreness(log.getMuscleSoreness())
                .recoveryScore(log.getRecoveryScore()).build();
    }
}
