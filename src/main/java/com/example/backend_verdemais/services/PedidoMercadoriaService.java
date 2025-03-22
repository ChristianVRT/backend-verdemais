package com.example.backend_verdemais.services;

import com.example.backend_verdemais.dto.PedidoMercadoriaDTO;
import com.example.backend_verdemais.entities.Mercadoria;
import com.example.backend_verdemais.entities.Pedido;
import com.example.backend_verdemais.entities.PedidoMercadoria;
import com.example.backend_verdemais.repositories.MercadoriaRepository;
import com.example.backend_verdemais.repositories.PedidoMercadoriaRepository;
import com.example.backend_verdemais.repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoMercadoriaService {
    @Autowired
    PedidoMercadoriaRepository pedidoMercadoriaRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    MercadoriaRepository mercadoriaRepository;

    public PedidoMercadoria adicionarMercadoria(PedidoMercadoriaDTO dto) {
        Pedido pedido = pedidoRepository.findById(dto.pedidoId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        Mercadoria mercadoria = mercadoriaRepository.findById(dto.mercadoriaId())
                .orElseThrow(() -> new RuntimeException("Mercadoria não encontrada"));

        PedidoMercadoria pedidoMercadoria = new PedidoMercadoria(null, pedido, mercadoria, dto.quantidade(), dto.precoUnitario());
        return pedidoMercadoriaRepository.save(pedidoMercadoria);
    }

    public List<PedidoMercadoria> listarItensDoPedido(Long pedidoId) {
        return pedidoMercadoriaRepository.findByPedidoId(pedidoId);
    }

    public void removerItem(Long id) {
        pedidoMercadoriaRepository.deleteById(id);
    }
}

