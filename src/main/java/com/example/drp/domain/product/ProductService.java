package com.example.drp.domain.product;

import com.example.drp.domain.customizedFields.CustomizedField;
import com.example.drp.domain.customizedFields.CustomizedFieldResponseDTO;
import com.example.drp.repositories.CustomizedFieldRepository;
import com.example.drp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CustomizedFieldRepository customizedFieldRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CustomizedFieldRepository customizedFieldRepository) {
        this.productRepository = productRepository;
        this.customizedFieldRepository = customizedFieldRepository;
    }

    public List<ProductResponseDTO> getAllProductsWithCustomizedFields() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(product -> {
                    List<CustomizedFieldResponseDTO> customizedFields = customizedFieldRepository.findCustomizedFieldsByProductId(product.getId());
                    return new ProductResponseDTO(product, customizedFields);
                })
                .collect(Collectors.toList());
    }
}
