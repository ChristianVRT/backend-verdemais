package com.example.backend_verdemais.controllers;

import com.example.backend_verdemais.dto.UsuarioResumidoDTO;
import com.example.backend_verdemais.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<List<UsuarioResumidoDTO>> getAllUsers() {
        List<UsuarioResumidoDTO> usuarios = usuarioService.getAllUsuariosResumidos();
        return ResponseEntity.ok(usuarios);
    }

}
