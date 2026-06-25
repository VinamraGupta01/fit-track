package com.fittrack.dto;

import com.fittrack.enums.Role;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

public class UserDtos {
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class UserResponse {
        private Long id;
        private String name;
        private String email;
        private Role role;
        private Double heightCm;
        private Double weightKg;
        private String goal;
        private LocalDateTime createdAt;
    }

    @Getter @Setter
    public static class UpdateProfileRequest {
        @NotBlank @Size(min = 2, max = 80)
        private String name;
        @DecimalMin("50.0") @DecimalMax("250.0")
        private Double heightCm;
        @DecimalMin("20.0") @DecimalMax("300.0")
        private Double weightKg;
        @Size(max = 160)
        private String goal;
    }

    @Getter @Setter
    public static class ChangePasswordRequest {
        @NotBlank
        private String currentPassword;
        @NotBlank @Size(min = 8)
        private String newPassword;
    }
}
