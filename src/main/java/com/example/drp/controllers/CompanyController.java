package com.example.drp.controllers;

import com.example.drp.dto.company.CompanyRequestDTO;
import com.example.drp.dto.company.CompanyResponseDTO;
import com.example.drp.entities.Company;
import com.example.drp.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyRepository repository;

    @PostMapping
    public void saveCompany(@RequestBody CompanyRequestDTO data) {
        Company companyData = new Company(data);
        repository.save(companyData);
        return;
    }

    @GetMapping
    public List<CompanyResponseDTO> getAll() {
        List<CompanyResponseDTO> companyList = repository.findAll().stream().map(CompanyResponseDTO::new).toList();

        return companyList;
    }

}
