package com.example.drp.domain.product;

public record ProductRequestDTO(
        double quantity,
        String name,
        String description,
        double price,
        Long companyId
) {}
