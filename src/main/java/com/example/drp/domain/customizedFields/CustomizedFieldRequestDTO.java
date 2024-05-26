package com.example.drp.domain.customizedFields;

public record CustomizedFieldRequestDTO (
        boolean active,
        String name,
        String label,
        boolean obligatory,
        FieldType type,
        long companyId
) {
}
