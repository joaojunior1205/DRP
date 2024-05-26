package com.example.drp.domain.customizedFields;

import com.example.drp.domain.user.User;

public class CustomizedFieldsService {

    public CustomizedField populateCustomizedField (CustomizedField customizedField, User user) {

        if (user != null) {
            customizedField.setCompanyId(user.getCompanyId());

            if (user.getId() != null) {
                customizedField.setAuthorId(user.getId());
                customizedField.setUpdateAuthorId(user.getId());
            }
        }

        return customizedField;
    }
}
