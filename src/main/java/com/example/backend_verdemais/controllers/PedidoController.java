package com.example.backend_verdemais.controllers;

import com.example.backend_verdemais.dto.request.PedidoRequestDTO;
import com.example.backend_verdemais.entities.Pedido;
import com.example.backend_verdemais.entities.Usuario;
import com.example.backend_verdemais.repositories.UsuarioRepository;
import com.example.backend_verdemais.security.TokenService;
import com.example.backend_verdemais.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<PedidoRequestDTO>> getAllPedidos() {
        return ResponseEntity.ok(pedidoService.getAllPedidos());
    }

    @PostMapping()
    public ResponseEntity<Pedido> createPedido(
            @RequestBody PedidoRequestDTO pedidoRequestDTO, @RequestHeader("Authorization") String token) {
        if (pedidoRequestDTO.mercadorias().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Usuario usuario = getUsuario(token);
        Pedido pedido = pedidoService.criarPedido(pedidoRequestDTO, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    public Usuario getUsuario(String token) {
        return usuarioRepository.findByEmail(tokenService.extrairEmail(token));
    }
}
