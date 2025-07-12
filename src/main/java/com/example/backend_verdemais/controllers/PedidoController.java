package com.example.backend_verdemais.controllers;

import com.example.backend_verdemais.dto.PedidoDTO;
import com.example.backend_verdemais.entities.PedidoMercadoria;
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

    @PostMapping
    public ResponseEntity<PedidoMercadoria> criarPedido(@RequestBody PedidoDTO pedidoDTO) {
        PedidoMercadoria pedido = pedidoService.criarPedido(pedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @GetMapping
    public ResponseEntity<List<PedidoMercadoria>> listarPedidos() {
        List<PedidoMercadoria> pedidos = pedidoService.listarPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoMercadoria> buscarPedidoPorId(@PathVariable Long id) {
        PedidoMercadoria pedido = pedidoService.buscarPedidoPorId(id);
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoMercadoria> atualizarPedido(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO) {
        PedidoMercadoria pedidoAtualizado = pedidoService.atualizarPedido(id, pedidoDTO);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        pedidoService.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
