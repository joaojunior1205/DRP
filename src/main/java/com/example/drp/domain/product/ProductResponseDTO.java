package com.example.drp.domain.product;

public record ProductResponseDTO(Long id, double quantity, String name, String description, double price) {

    public ProductResponseDTO(Product product) {
        this(
                product.getId(),
                product.getQuantity(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }
}