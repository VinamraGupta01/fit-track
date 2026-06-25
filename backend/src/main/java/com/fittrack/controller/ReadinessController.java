package com.fittrack.controller;

import com.fittrack.dto.ApiResponse;
import com.fittrack.dto.RecoveryDtos.ReadinessResponse;
import com.fittrack.service.ReadinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/readiness")
@RequiredArgsConstructor
public class ReadinessController {
    private final ReadinessService readinessService;

    @GetMapping("/today")
    public ResponseEntity<ApiResponse<ReadinessResponse>> today() {
        return ResponseEntity.ok(ApiResponse.success("Readiness score generated", readinessService.today()));
    }
}
