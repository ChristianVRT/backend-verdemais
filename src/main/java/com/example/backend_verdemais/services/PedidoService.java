package com.example.backend_verdemais.services;

import com.example.backend_verdemais.dto.MercadoriasPedidoDTO;
import com.example.backend_verdemais.dto.PedidoDTO;
import com.example.backend_verdemais.entities.Mercadoria;
import com.example.backend_verdemais.entities.MercadoriasPedido;
import com.example.backend_verdemais.entities.Pedido;
import com.example.backend_verdemais.entities.Usuario;
import com.example.backend_verdemais.repositories.MercadoriaRepository;
import com.example.backend_verdemais.repositories.MercadoriasPedidoRepository;
import com.example.backend_verdemais.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private MercadoriasPedidoRepository mercadoriasPedidoRepository;

    @Autowired
    private MercadoriaRepository mercadoriaRepository;

    public List<PedidoDTO> getAllPedidos() {return pedidoRepository.findAllPedidos();}

    public Pedido criarPedido(PedidoDTO pedidoDTO, Usuario cliente) {
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setData_pedido(pedidoDTO.dataPedido());
        pedido.setStatus("Pedido realizado");

        List<MercadoriasPedido> mercadorias = new ArrayList<>();
        double valorTotal = 0.0;

        for (MercadoriasPedidoDTO dto : pedidoDTO.mercadorias()) {
            Mercadoria mercadoria = mercadoriaRepository.findById(dto.idMercadoria())
                    .orElseThrow(() -> new IllegalArgumentException("Mercadoria não encontrada: ID " + dto.idMercadoria()));

            MercadoriasPedido mercadoriaPedido = new MercadoriasPedido();
            mercadoriaPedido.setPedido(pedido);
            mercadoriaPedido.setMercadoria(mercadoria);
            mercadoriaPedido.setQuantidade(dto.quantidade());
            mercadoriaPedido.setPrecoUnitario(dto.precoUnitario());

            mercadorias.add(mercadoriaPedido);
            valorTotal += dto.quantidade() * dto.precoUnitario();
        }

        pedido.setValor(valorTotal);
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        mercadorias.forEach(m -> m.setPedido(pedidoSalvo));
        mercadoriasPedidoRepository.saveAll(mercadorias);

        return pedidoSalvo;
    }

}
