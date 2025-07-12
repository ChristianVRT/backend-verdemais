package com.example.backend_verdemais.services;

import com.example.backend_verdemais.dto.PedidoDTO;
import com.example.backend_verdemais.entities.Pedido;
import com.example.backend_verdemais.entities.Usuario;
import com.example.backend_verdemais.repositories.PedidoRepository;
import com.example.backend_verdemais.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public Pedido criarPedido(PedidoDTO dto) {
        Usuario cliente = usuarioRepository.findById(dto.cliente().getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Pedido pedido = new Pedido(null, cliente, dto.data_pedido(), dto.valor(), dto.status(), new ArrayList<>());
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public Pedido atualizarPedido(Long id, PedidoDTO dto) {
        Pedido pedido = buscarPedidoPorId(id);
        pedido.setData(dto.data_pedido());
        pedido.setValor(dto.valor());
        pedido.setStatus(dto.status());
        return pedidoRepository.save(pedido);
    }

    public void deletarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
