package com.example.drp.domain.customizedFields;

public record CustomizedFieldResponseDTO (
        Long id,
        boolean active,
        String name,
        String label,
        boolean obligatory,
        FieldType type,
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
                value
        );
    }
}
