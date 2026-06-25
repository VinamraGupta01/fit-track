package com.fittrack.repository;

import com.fittrack.entity.RecoveryLog;
import com.fittrack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RecoveryLogRepository extends JpaRepository<RecoveryLog, Long> {
    Optional<RecoveryLog> findByUserAndLogDate(User user, LocalDate logDate);
    List<RecoveryLog> findByUserAndLogDateBetweenOrderByLogDateAsc(User user, LocalDate start, LocalDate end);
    Optional<RecoveryLog> findTopByUserOrderByLogDateDesc(User user);
    long countByLogDateBetween(LocalDate start, LocalDate end);
}
