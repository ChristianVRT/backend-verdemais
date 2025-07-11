package com.example.backend_verdemais.controllers;

import com.example.backend_verdemais.dto.request.LoginRequestDTO;
import com.example.backend_verdemais.dto.response.LoginResponseDTO;

import com.example.backend_verdemais.dto.request.SignupRequestDTO;
import com.example.backend_verdemais.entities.Usuario;
import com.example.backend_verdemais.repositories.UsuarioRepository;
import com.example.backend_verdemais.security.TokenService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static java.util.Objects.isNull;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO body){
        Usuario usuario = this.usuarioRepository.findByEmail(body.email());
                if (isNull(usuario)) throw new RuntimeException("Usuário não encontrado");
        if(passwordEncoder.matches(body.password(), usuario.getPassword())){
           String token = tokenService.generateToken(usuario);
           return ResponseEntity.ok(new LoginResponseDTO(usuario.getNome(), usuario.getEmail(), token, usuario.getRole()));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<LoginResponseDTO> signup(@RequestBody SignupRequestDTO body) {
        Usuario user = usuarioRepository.findByEmail(body.email());

        if (!isNull(user)) {
            Usuario newUsuario = new Usuario();
            newUsuario.setEmail(body.email());
            newUsuario.setPassword(passwordEncoder.encode(body.password()));
            newUsuario.setNome(body.name());
            newUsuario.setRole("USUARIO");
            usuarioRepository.save(newUsuario);

            String token = tokenService.generateToken(newUsuario);
            return ResponseEntity.ok(new LoginResponseDTO(newUsuario.getNome(), newUsuario.getEmail(), token, newUsuario.getRole()));
        }
        return ResponseEntity.badRequest().build();
    }
}
