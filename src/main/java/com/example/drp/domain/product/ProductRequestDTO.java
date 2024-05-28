package com.example.drp.domain.product;

import com.example.drp.domain.customizedFields.CustomizedFieldResponseDTO;

import java.util.List;

public record ProductRequestDTO(
        double quantity,
        String name,
        String description,
        double price,
        Long companyIdm,
        List<CustomizedFieldResponseDTO> customizedField
) {}
