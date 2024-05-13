package com.example.drp.controllers;

import com.example.drp.dto.user.UserRequestDTO;
import com.example.drp.dto.user.UserResponseDTO;
import com.example.drp.entities.User;
import com.example.drp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    public void saveUser (@RequestBody UserRequestDTO body) {
        User user = new User(body);

        user.setCompanyId(1);

        repository.save(user);
        return;
    }

    @GetMapping
    public List<UserResponseDTO> getAll () {
        List<UserResponseDTO> usersList = repository.findAll().stream().map(UserResponseDTO::new).toList();

        return usersList;
    }
}
