package com.example.drp.dto.user;

import com.example.drp.entities.User;

import java.sql.Timestamp;

public record UserResponseDTO (Long id, String name, String email, Timestamp lastAccess, Timestamp updatedAt, Timestamp createdAt, int companyId) {
    public UserResponseDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getLastAccess(), user.getUpdatedAt(), user.getCreatedAt(), user.getCompanyId());
    }
}
