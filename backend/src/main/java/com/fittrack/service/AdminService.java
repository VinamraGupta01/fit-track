package com.fittrack.service;

import com.fittrack.dto.UserDtos.UserResponse;
import com.fittrack.dto.WorkoutDtos.*;
import com.fittrack.entity.WorkoutCategory;
import com.fittrack.exception.BadRequestException;
import com.fittrack.exception.ResourceNotFoundException;
import com.fittrack.repository.UserRepository;
import com.fittrack.repository.WorkoutCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final WorkoutCategoryRepository categoryRepository;

    public Page<UserResponse> users(String search, Pageable pageable) {
        String q = search == null ? "" : search;
        return userRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(q, q, pageable).map(userService::map);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) throw new ResourceNotFoundException("User not found");
        userRepository.deleteById(id);
    }

    public CategoryResponse createCategory(CategoryRequest request) {
        categoryRepository.findByNameIgnoreCase(request.getName()).ifPresent(c -> { throw new BadRequestException("Category already exists"); });
        return map(categoryRepository.save(WorkoutCategory.builder().name(request.getName()).description(request.getDescription()).active(request.isActive()).build()));
    }

    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        WorkoutCategory c = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        c.setName(request.getName()); c.setDescription(request.getDescription()); c.setActive(request.isActive());
        return map(categoryRepository.save(c));
    }

    public void deleteCategory(Long id) { categoryRepository.deleteById(id); }
    public List<CategoryResponse> activeCategories() { return categoryRepository.findByActiveTrueOrderByNameAsc().stream().map(this::map).toList(); }
    public List<CategoryResponse> allCategories() { return categoryRepository.findAll().stream().map(this::map).toList(); }
    public CategoryResponse map(WorkoutCategory c) { return CategoryResponse.builder().id(c.getId()).name(c.getName()).description(c.getDescription()).active(c.isActive()).build(); }
}
