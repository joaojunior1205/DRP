package com.example.drp.entities;

import com.example.drp.dto.product.ProductRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "products")
@Entity(name = "product")
@Getter // Gera todos os metodos de get
@Setter // Gera os setters
@NoArgsConstructor // Declara um construtor q nao tem argumento
@AllArgsConstructor // Declara um construtor q recebe todos os argumentos
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "company_id")
    private long companyId;

    @Column(name = "author_id")
    private long authorId;

    @Column(name = "update_author_id")
    private long updateAuthorId;

    public Product(ProductRequestDTO data) {
        this.quantity = data.quantity();
        this.name = data.name();
        this.description = data.description();
        this.price = data.price();
    }
}
