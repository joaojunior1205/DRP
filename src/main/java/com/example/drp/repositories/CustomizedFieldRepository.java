package com.example.drp.repositories;

import com.example.drp.domain.customizedFields.CustomizedField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomizedFieldRepository extends JpaRepository<CustomizedField, Long> {
}
