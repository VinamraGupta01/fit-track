package com.fittrack.repository;

import com.fittrack.entity.ReadinessScore;
import com.fittrack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReadinessScoreRepository extends JpaRepository<ReadinessScore, Long> {
    Optional<ReadinessScore> findByUserAndScoreDate(User user, LocalDate scoreDate);
    List<ReadinessScore> findByUserAndScoreDateBetweenOrderByScoreDateAsc(User user, LocalDate start, LocalDate end);
    Optional<ReadinessScore> findTopByUserOrderByScoreDateDesc(User user);
    @Query("select coalesce(avg(r.score), 0) from ReadinessScore r")
    double averageScore();
}
