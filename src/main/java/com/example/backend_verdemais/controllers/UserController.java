package com.example.backend_verdemais.controllers;

import com.example.backend_verdemais.dto.request.UsuarioRequestDTO;
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
    public ResponseEntity<List<UsuarioRequestDTO>> getAllUsers() {
        List<UsuarioRequestDTO> usuarios = usuarioService.getAllUsuariosResumidos();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioRequestDTO> desativarUsuario(@RequestBody UsuarioRequestDTO usuarioDTO) {
        UsuarioRequestDTO usuario = usuarioService.updateUsuario(usuarioDTO);
        return ResponseEntity.ok(usuario);
    }

}
