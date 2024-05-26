package com.example.drp.domain.product;

import com.example.drp.domain.user.User;

public class ProductAction {

    public Product populateProduct (Product product, User user) {

        if (user != null) {
            product.setCompanyId(user.getCompanyId());
        }

        if (user != null && user.getId() != null) {
            product.setAuthorId(user.getId());
            product.setUpdateAuthorId(user.getId());
        }

        return product;
    }
}
