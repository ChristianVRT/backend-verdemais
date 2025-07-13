package com.example.backend_verdemais.services;

import com.example.backend_verdemais.dto.PedidoMercadoriaDTO;
import com.example.backend_verdemais.entities.Mercadoria;
import com.example.backend_verdemais.entities.MercadoriasPedido;
import com.example.backend_verdemais.entities.PedidoMercadoria;
import com.example.backend_verdemais.repositories.MercadoriaRepository;
import com.example.backend_verdemais.repositories.MercadoriasPedidoRepository;
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

    @Autowired
    MercadoriasPedidoRepository mercadoriasPedidoRepository;

    public PedidoMercadoria adicionarMercadoria(PedidoMercadoriaDTO dto) {
        PedidoMercadoria pedido = pedidoRepository.findById(dto.pedidoId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        Mercadoria mercadoria = mercadoriaRepository.findById(dto.mercadoriaId())
                .orElseThrow(() -> new RuntimeException("Mercadoria não encontrada"));

        MercadoriasPedido mercadoriasPedido = new MercadoriasPedido();
        mercadoriasPedido.setPedido(pedido);
        mercadoriasPedido.setMercadoria(mercadoria);
        mercadoriasPedido.setQuantidade(dto.quantidade());
        mercadoriasPedido.setPreco(dto.precoUnitario());

        pedido.getMercadoriasPedidos().add(mercadoriasPedido);

        return pedidoMercadoriaRepository.save(pedido);
    }

    public List<MercadoriasPedido> listarItensDoPedido(Long pedidoId) {
        return mercadoriasPedidoRepository.findByPedido_Id(pedidoId);
    }

    public void removerItem(Long id) {
        pedidoMercadoriaRepository.deleteById(id);
    }
}

