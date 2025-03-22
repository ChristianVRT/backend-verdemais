package com.example.backend_verdemais.controllers;

import com.example.backend_verdemais.dto.UsuarioDTO;
import com.example.backend_verdemais.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<List<UsuarioDTO>> getAllUsers() {
        List<UsuarioDTO> usuarios = usuarioService.getAllUsuariosResumidos();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> desativarUsuario(@PathVariable Long id, UsuarioDTO usuarioDTO) {
        UsuarioDTO usuario = usuarioService.desativarUsuario(id, usuarioDTO);
        return ResponseEntity.ok(usuario);
    }

}
