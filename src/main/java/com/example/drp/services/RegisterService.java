package com.example.drp.services;

import com.example.drp.domain.user.UserRole;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    public boolean isValidRole(String role) {
        for (UserRole validRole : UserRole.values()) {
            if (validRole.getRole().equals(role)) {
                return true;
            }
        }

        return false;
    }

}
