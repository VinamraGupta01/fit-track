package com.fittrack.service;

import com.fittrack.dto.WorkoutDtos.*;
import com.fittrack.entity.User;
import com.fittrack.entity.Workout;
import com.fittrack.entity.WorkoutCategory;
import com.fittrack.exception.ResourceNotFoundException;
import com.fittrack.repository.WorkoutCategoryRepository;
import com.fittrack.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final WorkoutCategoryRepository categoryRepository;
    private final CurrentUserService currentUserService;

    public WorkoutResponse create(WorkoutRequest request) {
        User user = currentUserService.getCurrentUser();
        Workout workout = Workout.builder()
                .user(user).category(findCategory(request.getCategoryId()))
                .title(request.getTitle()).intensity(request.getIntensity())
                .durationMinutes(request.getDurationMinutes()).caloriesBurned(request.getCaloriesBurned())
                .workoutDate(request.getWorkoutDate()).notes(request.getNotes()).build();
        return map(workoutRepository.save(workout));
    }

    public Page<WorkoutResponse> list(String search, Pageable pageable) {
        User user = currentUserService.getCurrentUser();
        return workoutRepository.findByUserAndTitleContainingIgnoreCase(user, search == null ? "" : search, pageable).map(this::map);
    }

    public WorkoutResponse update(Long id, WorkoutRequest request) {
        User user = currentUserService.getCurrentUser();
        Workout workout = workoutRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Workout not found"));
        if (!workout.getUser().getId().equals(user.getId())) throw new ResourceNotFoundException("Workout not found");
        workout.setCategory(findCategory(request.getCategoryId()));
        workout.setTitle(request.getTitle());
        workout.setIntensity(request.getIntensity());
        workout.setDurationMinutes(request.getDurationMinutes());
        workout.setCaloriesBurned(request.getCaloriesBurned());
        workout.setWorkoutDate(request.getWorkoutDate());
        workout.setNotes(request.getNotes());
        return map(workoutRepository.save(workout));
    }

    public void delete(Long id) {
        User user = currentUserService.getCurrentUser();
        Workout workout = workoutRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Workout not found"));
        if (!workout.getUser().getId().equals(user.getId())) throw new ResourceNotFoundException("Workout not found");
        workoutRepository.delete(workout);
    }

    private WorkoutCategory findCategory(Long id) { return id == null ? null : categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found")); }

    public WorkoutResponse map(Workout w) {
        return WorkoutResponse.builder().id(w.getId()).title(w.getTitle()).categoryId(w.getCategory()==null?null:w.getCategory().getId())
                .categoryName(w.getCategory()==null?"General":w.getCategory().getName()).intensity(w.getIntensity())
                .durationMinutes(w.getDurationMinutes()).caloriesBurned(w.getCaloriesBurned()).workoutDate(w.getWorkoutDate())
                .notes(w.getNotes()).createdAt(w.getCreatedAt()).build();
    }
}
