package com.example.drp.controllers;

import com.example.drp.domain.customizedFields.CustomizedField;
import com.example.drp.domain.customizedFields.CustomizedFieldResponseDTO;
import com.example.drp.domain.customizedFields.ProductCustomizedFieldDTO;
import com.example.drp.domain.product.*;
import com.example.drp.domain.user.User;
import com.example.drp.infra.security.TokenService;
import com.example.drp.repositories.CustomizedFieldRepository;
import com.example.drp.repositories.ProductCustomizedFieldRepository;
import com.example.drp.repositories.ProductRepository;
import com.example.drp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomizedFieldRepository customizedFieldRepository;

    @Autowired
    private ProductCustomizedFieldRepository productCustomizedFieldRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity saveProduct(@RequestHeader("Authorization") String authorizationHeader, @RequestBody ProductRequestDTO productRequest) {
        try {
            if (productRequest.name() == null) {
                return ResponseEntity.status(409).body("Fill in all mandatory fields");
            }

            Product productData = new Product(productRequest);

            String username = new TokenService().extractUsername(authorizationHeader);
            User user = userRepository.findUserByEmail(username);

            Product product = new ProductAction().populateProduct(productData, user);

            Product savedProduct = productRepository.save(product);

            if (productRequest.customizedField() != null) {
                productRequest.customizedField().forEach(item -> {
                    if (item.id() != null && item.value() != null &&  savedProduct.getId() != 0) {
                        ProductCustomizedField customizedFieldWithValue = new ProductCustomizedField();

                        customizedFieldWithValue.setProductId(savedProduct.getId());
                        customizedFieldWithValue.setCustomizedFieldId(item.id());
                        customizedFieldWithValue.setValue(item.value());

                        productCustomizedFieldRepository.save(customizedFieldWithValue);
                    }
                });
            }

            return ResponseEntity.ok().body(savedProduct);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public List<ProductResponseDTO> getAll() {
        List<ProductResponseDTO> productList = productRepository.findAll().stream().map(product -> {
            List<Object[]> getCustomfields = customizedFieldRepository.findCustomizedFieldsByProductId(product.getId());
            List<CustomizedFieldResponseDTO> customFields = new java.util.ArrayList<>();

            for (Object[] getCustomfield : getCustomfields) {
                customFields.add(new CustomizedFieldResponseDTO((CustomizedField) getCustomfield[0], (String) getCustomfield[1]));
            }

            return new ProductResponseDTO(product, customFields);
        }).toList();


        return productList;
    }
}
