package com.fittrack.controller;

import com.fittrack.dto.ApiResponse;
import com.fittrack.dto.DashboardDtos.*;
import com.fittrack.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<SummaryResponse>> summary() {
        return ResponseEntity.ok(ApiResponse.success("Dashboard summary fetched", dashboardService.summary()));
    }

    @GetMapping("/trends")
    public ResponseEntity<ApiResponse<TrendsResponse>> trends() {
        return ResponseEntity.ok(ApiResponse.success("Dashboard trends fetched", dashboardService.trends()));
    }
}
