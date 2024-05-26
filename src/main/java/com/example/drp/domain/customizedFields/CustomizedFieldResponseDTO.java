package com.example.drp.domain.customizedFields;

public record CustomizedFieldResponseDTO (
        Long id,
        boolean active,
        String name,
        String label,
        boolean obligatory,
        FieldType type
) {
    public CustomizedFieldResponseDTO (CustomizedField customizedField) {
        this(
                customizedField.getId(),
                customizedField.isActive(),
                customizedField.getName(),
                customizedField.getLabel(),
                customizedField.isObligatory(),
                customizedField.getType()
        );
    }
}
