package com.example.drp.domain.customizedFields;

import java.sql.Timestamp;

public record CustomizedFieldResponseDTO (
        Long id,
        boolean active,
        String name,
        String label,
        boolean obligatory,
        FieldType type,
        Timestamp createdAt,
        Timestamp updateAt,
        String value
) {
    public CustomizedFieldResponseDTO (CustomizedField customizedField) {
        this(
                customizedField.getId(),
                customizedField.isActive(),
                customizedField.getName(),
                customizedField.getLabel(),
                customizedField.isObligatory(),
                customizedField.getType(),
                customizedField.getCreatedAt(),
                customizedField.getCreatedAt(),
                null
        );
    }

    public CustomizedFieldResponseDTO (CustomizedField customizedField, String value) {
        this(
                customizedField.getId(),
                customizedField.isActive(),
                customizedField.getName(),
                customizedField.getLabel(),
                customizedField.isObligatory(),
                customizedField.getType(),
                customizedField.getCreatedAt(),
                customizedField.getCreatedAt(),
                value
        );
    }
}
