package com.example.drp.controllers;

import com.example.drp.domain.user.UserResponseDTO;
import com.example.drp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public List<UserResponseDTO> getAll () {
        List<UserResponseDTO> usersList = repository.findAll().stream().map(UserResponseDTO::new).toList();

        return usersList;
    }
}
