package com.example.backend_verdemais.controllers;

import com.example.backend_verdemais.dto.LoginRequestDTO;
import com.example.backend_verdemais.dto.ResponseDTO;

import com.example.backend_verdemais.dto.SignupRequestDTO;
import com.example.backend_verdemais.entities.User;
import com.example.backend_verdemais.repositories.UserRepository;
import com.example.backend_verdemais.security.TokenService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body){
        User user = this.userRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if(passwordEncoder.matches(body.password(), user.getPassword())){
           String token = tokenService.generateToken(user);
           return ResponseEntity.ok(new ResponseDTO(user.getName(), user.getEmail(), token, user.getRole()));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> signup(@RequestBody SignupRequestDTO body) {
        Optional<User> user = userRepository.findByEmail(body.email());

        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setEmail(body.email());
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setName(body.name());
            newUser.setRole("USUARIO");
            userRepository.save(newUser);

            String token = tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), newUser.getEmail(), token, newUser.getRole()));
        }
        return ResponseEntity.badRequest().build();
    }
}
