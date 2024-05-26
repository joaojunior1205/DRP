package com.example.drp.domain.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "product_customized_field")
@Entity(name = "ProductCustomizedField")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCustomizedField {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "customized_field_id")
    private long customizedFieldId;

    @Column(name = "value")
    private String value;
}
