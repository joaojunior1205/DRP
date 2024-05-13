package com.example.drp.dto.product;

public record ProductRequestDTO(
        double quantity,
        String name,
        String description,
        double price,
        Long companyId
) {}
