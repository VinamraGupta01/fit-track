package com.fittrack.service;

import com.fittrack.dto.AuthDtos.*;
import com.fittrack.entity.User;
import com.fittrack.enums.Role;
import com.fittrack.exception.BadRequestException;
import com.fittrack.repository.UserRepository;
import com.fittrack.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) throw new BadRequestException("Email already registered");
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail().toLowerCase())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .goal("Improve consistency and recovery")
                .build();
        userRepository.save(user);
        return tokenFor(user);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail().toLowerCase(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail().toLowerCase()).orElseThrow(() -> new BadRequestException("Invalid credentials"));
        return tokenFor(user);
    }

    private AuthResponse tokenFor(User user) {
        var details = userDetailsService.loadUserByUsername(user.getEmail());
        String token = jwtService.generateToken(details, Map.of("role", user.getRole().name(), "userId", user.getId()));
        return AuthResponse.builder().accessToken(token).tokenType("Bearer").userId(user.getId()).name(user.getName()).email(user.getEmail()).role(user.getRole()).build();
    }
}
