package com.fittrack.dto;

import com.fittrack.enums.Role;
import jakarta.validation.constraints.*;
import lombok.*;

public class AuthDtos {
    @Getter @Setter
    public static class RegisterRequest {
        @NotBlank @Size(min = 2, max = 80)
        private String name;
        @Email @NotBlank
        private String email;
        @NotBlank @Size(min = 8, message = "Password must contain at least 8 characters")
        private String password;
    }

    @Getter @Setter
    public static class LoginRequest {
        @Email @NotBlank
        private String email;
        @NotBlank
        private String password;
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class AuthResponse {
        private String accessToken;
        private String tokenType;
        private Long userId;
        private String name;
        private String email;
        private Role role;
    }
}
