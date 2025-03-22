package com.example.backend_verdemais.controllers;

import com.example.backend_verdemais.dto.PedidoMercadoriaDTO;
import com.example.backend_verdemais.entities.PedidoMercadoria;
import com.example.backend_verdemais.services.PedidoMercadoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido-mercadorias")
@RequiredArgsConstructor
public class PedidoMercadoriaController {
    @Autowired
    PedidoMercadoriaService pedidoMercadoriaService;

    @PostMapping
    public ResponseEntity<PedidoMercadoria> adicionarMercadoria(@RequestBody PedidoMercadoriaDTO dto) {
        PedidoMercadoria pedidoMercadoria = pedidoMercadoriaService.adicionarMercadoria(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoMercadoria);
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<List<PedidoMercadoria>> listarItensDoPedido(@PathVariable Long pedidoId) {
        List<PedidoMercadoria> itens = pedidoMercadoriaService.listarItensDoPedido(pedidoId);
        return ResponseEntity.ok(itens);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerItem(@PathVariable Long id) {
        pedidoMercadoriaService.removerItem(id);
        return ResponseEntity.noContent().build();
    }
}

