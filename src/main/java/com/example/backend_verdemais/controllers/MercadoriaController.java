package com.example.backend_verdemais.controllers;

import com.example.backend_verdemais.dto.MercadoriaDTO;
import com.example.backend_verdemais.entities.Usuario;
import com.example.backend_verdemais.repositories.UsuarioRepository;
import com.example.backend_verdemais.security.SecurityFilter;
import com.example.backend_verdemais.security.TokenService;
import com.example.backend_verdemais.services.MercadoriaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mercadoria")
public class MercadoriaController {

    @Autowired
    MercadoriaService mercadoriaService;

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping()
    public ResponseEntity<List<MercadoriaDTO>> getAllMercadorias() {
        List<MercadoriaDTO> mercadorias = mercadoriaService.getAllMercadorias();
        return ResponseEntity.ok(mercadorias);
    }

    @PostMapping()
    public ResponseEntity<MercadoriaDTO> postMercadoria(
            @RequestBody MercadoriaDTO mercadoriaDTO, @RequestHeader("Authorization") String token
    ) {
        Usuario usuario = getUsuario(token);
        MercadoriaDTO mercadoria = mercadoriaService.postMercadoria(mercadoriaDTO, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(mercadoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MercadoriaDTO> updateMercadoria(
            @PathVariable Long id, @RequestBody MercadoriaDTO mercadoriaDTO, @RequestHeader("Authorization") String token) {
        Usuario usuario = getUsuario(token);
        MercadoriaDTO mercadoria = mercadoriaService.updateMercadoria(id, mercadoriaDTO, usuario);
        return ResponseEntity.status(HttpStatus.OK).body(mercadoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMercadoria(@PathVariable Long id) {
        mercadoriaService.deleteMercadoria(id);
        return ResponseEntity.noContent().build();
    }

    public Usuario getUsuario(String token) {
        String email = tokenService.extrairEmail(token);
        System.out.println("Email extraído do token: " + email);  // Adicione um log para verificar o email
        return usuarioRepository.findByEmail(email);
    }

}
