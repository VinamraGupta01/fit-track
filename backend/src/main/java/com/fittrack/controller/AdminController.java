package com.fittrack.controller;

import com.fittrack.dto.ApiResponse;
import com.fittrack.dto.DashboardDtos.PlatformStatsResponse;
import com.fittrack.dto.UserDtos.UserResponse;
import com.fittrack.dto.WorkoutDtos.*;
import com.fittrack.service.AdminService;
import com.fittrack.service.DashboardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final DashboardService dashboardService;

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<Page<UserResponse>>> users(@RequestParam(required = false) String search, Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success("Users fetched", adminService.users(search, pageable)));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok(ApiResponse.success("User deleted", null));
    }

    @GetMapping("/statistics")
    public ResponseEntity<ApiResponse<PlatformStatsResponse>> statistics() {
        return ResponseEntity.ok(ApiResponse.success("Platform statistics fetched", dashboardService.platformStats()));
    }

    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> categories() {
        return ResponseEntity.ok(ApiResponse.success("Categories fetched", adminService.allCategories()));
    }

    @PostMapping("/categories")
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(@Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Category created", adminService.createCategory(request)));
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Category updated", adminService.updateCategory(id, request)));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Long id) {
        adminService.deleteCategory(id);
        return ResponseEntity.ok(ApiResponse.success("Category deleted", null));
    }
}
