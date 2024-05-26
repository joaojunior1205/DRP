package com.example.drp.controllers;

import com.example.drp.domain.customizedFields.CustomizedField;
import com.example.drp.domain.customizedFields.CustomizedFieldRequestDTO;
import com.example.drp.domain.customizedFields.CustomizedFieldResponseDTO;
import com.example.drp.domain.customizedFields.CustomizedFieldsService;
import com.example.drp.domain.user.User;
import com.example.drp.infra.security.TokenService;
import com.example.drp.repositories.CustomizedFieldRepository;
import com.example.drp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/customizedField")
public class CustomizedFieldsController {

    @Autowired
    private CustomizedFieldRepository customizedFieldRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity getAll() {
        try {

            List<CustomizedFieldResponseDTO> customizedFieldsList = customizedFieldRepository.findAll().stream().map(CustomizedFieldResponseDTO::new).toList();

            return ResponseEntity.ok(customizedFieldsList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestHeader("Authorization") String authorizationHeader, @RequestBody CustomizedFieldRequestDTO customizedFieldRequest) {
        try {
            if (customizedFieldRequest.name() == null || customizedFieldRequest.label() == null || customizedFieldRequest.type() == null) {
                return ResponseEntity.status(409).body("Customized field name or label or type is not valid");
            }

            String userName = new TokenService().extractUsername(authorizationHeader);
            User user = userRepository.findUserByEmail(userName);

            CustomizedField customizedField = new CustomizedField(customizedFieldRequest);

            customizedField = new CustomizedFieldsService().populateCustomizedField(customizedField, user);

            long currentTimeMillis = System.currentTimeMillis();
            long offsetMillis = -3 * 60 * 60 * 1000;
            Timestamp timestamp = new Timestamp(currentTimeMillis + offsetMillis);

            customizedField.setUpdatedAt(timestamp);
            customizedField.setCreatedAt(timestamp);

            return ResponseEntity.ok(customizedFieldRepository.save(customizedField));
        } catch (Exception err) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
