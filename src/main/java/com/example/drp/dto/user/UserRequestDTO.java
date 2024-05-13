package com.example.drp.dto.user;

public record UserRequestDTO (
        String name,
        String email,
        String password,
        String createdAt
) {
}
