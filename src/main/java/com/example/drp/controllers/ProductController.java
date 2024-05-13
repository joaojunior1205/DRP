package com.example.drp.controllers;

import com.example.drp.dto.product.ProductRequestDTO;
import com.example.drp.dto.product.ProductResponseDTO;
import com.example.drp.entities.Product;
import com.example.drp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @PostMapping
    public void saveProduct(@RequestBody ProductRequestDTO data) {
        Product productData = new Product(data);

        productData.setCompanyId(1);
        productData.setAuthorId(7);
        productData.setUpdateAuthorId(7);

        repository.save(productData);
        return;
    }

    @GetMapping
    public List<ProductResponseDTO> getAll() {
        List<ProductResponseDTO> productList = repository.findAll().stream().map(ProductResponseDTO::new).toList();

        return productList;
    }
}
