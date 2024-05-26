package com.example.drp.domain.product;

import com.example.drp.domain.customizedFields.CustomizedFieldResponseDTO;

import java.util.ArrayList;
import java.util.List;

public record ProductResponseDTO(Long id, double quantity, String name, String description, double price, List<CustomizedFieldResponseDTO> customizedFields) {

    public ProductResponseDTO(Product product) {
        this(
                product.getId(),
                product.getQuantity(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                new ArrayList<>()
        );
    }

    public ProductResponseDTO(Product product, List<CustomizedFieldResponseDTO> customizedFields) {
        this(
                product.getId(),
                product.getQuantity(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                customizedFields
        );
    }
}