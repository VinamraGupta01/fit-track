package com.fittrack.entity;

import com.fittrack.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "users", indexes = @Index(name = "idx_users_email", columnList = "email"))
public class User extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private Double heightCm;
    private Double weightKg;
    private String goal;
}
