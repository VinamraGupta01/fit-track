package com.fittrack.repository;

import com.fittrack.entity.WorkoutCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface WorkoutCategoryRepository extends JpaRepository<WorkoutCategory, Long> {
    Optional<WorkoutCategory> findByNameIgnoreCase(String name);
    List<WorkoutCategory> findByActiveTrueOrderByNameAsc();
}
