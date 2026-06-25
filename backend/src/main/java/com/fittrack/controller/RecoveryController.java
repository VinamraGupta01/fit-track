package com.fittrack.controller;

import com.fittrack.dto.ApiResponse;
import com.fittrack.dto.RecoveryDtos.*;
import com.fittrack.service.RecoveryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recovery")
@RequiredArgsConstructor
public class RecoveryController {
    private final RecoveryService recoveryService;

    @PostMapping
    public ResponseEntity<ApiResponse<RecoveryResponse>> createOrUpdate(@Valid @RequestBody RecoveryRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Recovery log saved", recoveryService.upsert(request)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RecoveryResponse>>> list() {
        return ResponseEntity.ok(ApiResponse.success("Recovery logs fetched", recoveryService.listLast30Days()));
    }
}
