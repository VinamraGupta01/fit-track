package com.fittrack.controller;

import com.fittrack.dto.ApiResponse;
import com.fittrack.dto.WorkoutDtos.*;
import com.fittrack.service.AdminService;
import com.fittrack.service.WorkoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@RequiredArgsConstructor
public class WorkoutController {
    private final WorkoutService workoutService;
    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<ApiResponse<WorkoutResponse>> create(@Valid @RequestBody WorkoutRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Workout created", workoutService.create(request)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<WorkoutResponse>>> list(@RequestParam(required = false) String search, Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success("Workout history fetched", workoutService.list(search, pageable)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<WorkoutResponse>> update(@PathVariable Long id, @Valid @RequestBody WorkoutRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Workout updated", workoutService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        workoutService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Workout deleted", null));
    }

    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> categories() {
        return ResponseEntity.ok(ApiResponse.success("Categories fetched", adminService.activeCategories()));
    }
}
