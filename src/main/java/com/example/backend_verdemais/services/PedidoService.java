package com.example.backend_verdemais.services;

import com.example.backend_verdemais.dto.PedidoDTO;
import com.example.backend_verdemais.entities.PedidoMercadoria;
import com.example.backend_verdemais.entities.Usuario;
import com.example.backend_verdemais.repositories.PedidoRepository;
import com.example.backend_verdemais.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public PedidoMercadoria criarPedido(PedidoDTO dto) {
        Usuario cliente = usuarioRepository.findById(dto.cliente().getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        PedidoMercadoria pedido = new PedidoMercadoria(null, cliente, dto.data_pedido(), dto.valor(), dto.status(), new ArrayList<>());
        return pedidoRepository.save(pedido);
    }

    public List<PedidoMercadoria> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public PedidoMercadoria buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public PedidoMercadoria atualizarPedido(Long id, PedidoDTO dto) {
        PedidoMercadoria pedido = buscarPedidoPorId(id);
        pedido.setData(dto.data_pedido());
        pedido.setValor(dto.valor());
        pedido.setStatus(dto.status());
        return pedidoRepository.save(pedido);
    }

    public void deletarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
