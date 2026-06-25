package com.fittrack.service;

import com.fittrack.dto.UserDtos.*;
import com.fittrack.entity.User;
import com.fittrack.exception.BadRequestException;
import com.fittrack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final CurrentUserService currentUserService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse profile() { return map(currentUserService.getCurrentUser()); }

    public UserResponse update(UpdateProfileRequest request) {
        User user = currentUserService.getCurrentUser();
        user.setName(request.getName());
        user.setHeightCm(request.getHeightCm());
        user.setWeightKg(request.getWeightKg());
        user.setGoal(request.getGoal());
        return map(userRepository.save(user));
    }

    public void changePassword(ChangePasswordRequest request) {
        User user = currentUserService.getCurrentUser();
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) throw new BadRequestException("Current password is incorrect");
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    public UserResponse map(User user) {
        return UserResponse.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).role(user.getRole())
                .heightCm(user.getHeightCm()).weightKg(user.getWeightKg()).goal(user.getGoal()).createdAt(user.getCreatedAt()).build();
    }
}
