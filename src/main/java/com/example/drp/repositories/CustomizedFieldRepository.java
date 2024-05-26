package com.example.drp.repositories;

import com.example.drp.domain.customizedFields.CustomizedField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomizedFieldRepository extends JpaRepository<CustomizedField, Long> {
    @Query("SELECT cf, pcf.value FROM customizedField cf JOIN ProductCustomizedField pcf ON cf.id = pcf.customizedFieldId WHERE pcf.productId = :productId")
    List<Object[]> findCustomizedFieldsByProductId(Long productId);
}
