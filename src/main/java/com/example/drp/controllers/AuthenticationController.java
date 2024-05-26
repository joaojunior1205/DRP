package com.example.drp.controllers;

import com.example.drp.domain.user.AuthenticationDTO;
import com.example.drp.domain.user.RegisterDTO;
import com.example.drp.domain.user.User;
import com.example.drp.domain.user.UserRole;
import com.example.drp.infra.security.TokenService;
import com.example.drp.repositories.UserRepository;
import com.example.drp.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data) {
        try {
            RegisterService registerService = new RegisterService();

            if (data.name() == null || data.role() == null || data.email() == null || data.password() == null) {
                return ResponseEntity.status(409).body("Fill in all mandatory fields");
            }

            if (!registerService.isValidRole(data.role())) {
                return ResponseEntity.status(409).body("Role is invalid");
            }

            if (this.userRepository.findByEmail(data.email()) != null) {
                return ResponseEntity.status(409).body("User exist");
            }

            String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
            User newUser = new User(data.name(), data.email(), encryptedPassword, UserRole.valueOf(data.role().toUpperCase()));

            newUser.setCompanyId(1);

            this.userRepository.save(newUser);

            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
