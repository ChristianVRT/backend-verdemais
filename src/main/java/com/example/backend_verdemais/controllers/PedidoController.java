package com.example.backend_verdemais.controllers;

import com.example.backend_verdemais.dto.PedidoDTO;
import com.example.backend_verdemais.entities.Usuario;
import com.example.backend_verdemais.repositories.UsuarioRepository;
import com.example.backend_verdemais.security.TokenService;
import com.example.backend_verdemais.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TokenService tokenService;

    @GetMapping()
    public ResponseEntity<List<PedidoDTO>> getAllPedidos() {
        return ResponseEntity.ok(pedidoService.getAllPedidos());
    }

    @PostMapping()
    public ResponseEntity<PedidoDTO> createPedido(
            @RequestBody PedidoDTO pedidoDTO, @RequestHeader("Authorization") String token) {
        if (pedidoDTO.mercadorias().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Usuario usuario = getUsuario(token);
        pedidoService.criarPedido(pedidoDTO, usuario);
        return ResponseEntity.ok(pedidoDTO);
    }

    public Usuario getUsuario(String token) {
        return usuarioRepository.findByEmail(tokenService.extrairEmail(token));
    }
}
