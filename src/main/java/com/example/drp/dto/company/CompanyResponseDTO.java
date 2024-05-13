package com.example.drp.dto.company;

import com.example.drp.entities.Company;

public record CompanyResponseDTO(Long id, String name, String cnpj, String phone) {

    public CompanyResponseDTO(Company company) {
        this(company.getId(), company.getName(), company.getCnpj(), company.getPhone());
    }
}
