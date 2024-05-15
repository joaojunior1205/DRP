package com.example.drp.controllers;

import com.example.drp.domain.company.CompanyRequestDTO;
import com.example.drp.domain.company.CompanyResponseDTO;
import com.example.drp.domain.company.Company;
import com.example.drp.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyRepository repository;

    @PostMapping
    public ResponseEntity saveCompany(@RequestBody CompanyRequestDTO data) {
        try {
            Company company = new Company(data);

            if (company.getName() == null) {
                return ResponseEntity.status(409).body("Fill in all mandatory fields");
            }

            repository.save(company);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity getAll() {
        try {
            List<CompanyResponseDTO> companyList = repository.findAll().stream().map(CompanyResponseDTO::new).toList();

            return ResponseEntity.ok(companyList);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
