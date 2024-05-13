package com.example.drp.domain.company;

public record CompanyResponseDTO(Long id, String name, String cnpj, String phone) {

    public CompanyResponseDTO(Company company) {
        this(company.getId(), company.getName(), company.getCnpj(), company.getPhone());
    }
}
