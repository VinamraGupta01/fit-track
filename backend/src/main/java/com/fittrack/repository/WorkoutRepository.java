package com.fittrack.repository;

import com.fittrack.entity.User;
import com.fittrack.entity.Workout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    Page<Workout> findByUserAndTitleContainingIgnoreCase(User user, String search, Pageable pageable);
    long countByUser(User user);
    long countByUserAndWorkoutDateBetween(User user, LocalDate start, LocalDate end);
    long countByWorkoutDateBetween(LocalDate start, LocalDate end);
    List<Workout> findByUserAndWorkoutDateBetween(User user, LocalDate start, LocalDate end);
    @Query("select coalesce(sum(w.durationMinutes),0) from Workout w where w.user=:user and w.workoutDate between :start and :end")
    int totalMinutes(@Param("user") User user, @Param("start") LocalDate start, @Param("end") LocalDate end);
}
