package com.example.drp.controllers;

import com.example.drp.domain.product.ProductRequestDTO;
import com.example.drp.domain.product.ProductResponseDTO;
import com.example.drp.domain.product.Product;
import com.example.drp.domain.user.User;
import com.example.drp.infra.security.TokenService;
import com.example.drp.repositories.ProductRepository;
import com.example.drp.repositories.UserRepository;
import com.example.drp.services.ProductService;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity saveProduct(@RequestHeader("Authorization") String authorizationHeader, @RequestBody ProductRequestDTO productDTO) {
        try {
            if (productDTO.name() == null) {
                return ResponseEntity.status(409).body("Fill in all mandatory fields");
            }

            Product productData = new Product(productDTO);

            String username = new TokenService().extractUsername(authorizationHeader);
            User user = userRepository.findUserByEmail(username);

            Product product = new ProductService().populateProduct(productData, user);

            return ResponseEntity.ok().body(productRepository.save(product));
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public List<ProductResponseDTO> getAll() {
        List<ProductResponseDTO> productList = productRepository.findAll().stream().map(ProductResponseDTO::new).toList();

        return productList;
    }
}
