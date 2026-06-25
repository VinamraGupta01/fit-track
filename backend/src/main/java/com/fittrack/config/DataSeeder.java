package com.fittrack.config;

import com.fittrack.entity.User;
import com.fittrack.entity.WorkoutCategory;
import com.fittrack.enums.Role;
import com.fittrack.repository.UserRepository;
import com.fittrack.repository.WorkoutCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataSeeder {
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner seed(UserRepository users, WorkoutCategoryRepository categories) {
        return args -> {
            if (!users.existsByEmail("admin@fittrack.dev")) {
                users.save(User.builder().name("Fit Track Admin").email("admin@fittrack.dev").password(passwordEncoder.encode("Admin@123")).role(Role.ADMIN).goal("Manage platform quality").build());
            }
            if (!users.existsByEmail("user@fittrack.dev")) {
                users.save(User.builder().name("Demo Athlete").email("user@fittrack.dev").password(passwordEncoder.encode("User@123")).role(Role.USER).heightCm(178.0).weightKg(74.0).goal("Build strength while improving recovery").build());
            }
            if (categories.count() == 0) {
                categories.save(WorkoutCategory.builder().name("Strength").description("Resistance and hypertrophy training").active(true).build());
                categories.save(WorkoutCategory.builder().name("Cardio").description("Endurance, runs, rides and conditioning").active(true).build());
                categories.save(WorkoutCategory.builder().name("Mobility").description("Flexibility and active recovery").active(true).build());
            }
        };
    }
}
