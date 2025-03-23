package com.example.backend_verdemais.services;

import com.example.backend_verdemais.dto.request.MercadoriasPedidoRequestDTO;
import com.example.backend_verdemais.dto.request.PedidoRequestDTO;
import com.example.backend_verdemais.entities.Mercadoria;
import com.example.backend_verdemais.entities.MercadoriasPedido;
import com.example.backend_verdemais.entities.Pedido;
import com.example.backend_verdemais.entities.Usuario;
import com.example.backend_verdemais.exception.CustomException;
import com.example.backend_verdemais.exception.ErrorCode;
import com.example.backend_verdemais.repositories.MercadoriaRepository;
import com.example.backend_verdemais.repositories.MercadoriasPedidoRepository;
import com.example.backend_verdemais.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private MercadoriasPedidoRepository mercadoriasPedidoRepository;

    @Autowired
    private MercadoriaRepository mercadoriaRepository;

    public List<PedidoRequestDTO> getAllPedidos() {
        return pedidoRepository.findAllPedidos();
    }

    @Transactional
    public Pedido criarPedido(PedidoRequestDTO pedidoRequestDTO, Usuario cliente) {
        ZonedDateTime now = ZonedDateTime.now(java.time.ZoneId.of("America/Sao_Paulo"));
        Long dataPedidoLong = now.toInstant().toEpochMilli();

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setData_pedido(dataPedidoLong);
        pedido.setStatus("Pedido realizado");

        List<MercadoriasPedido> mercadorias = new ArrayList<>();
        double valorTotal = 0.0;

        for (MercadoriasPedidoRequestDTO dto : pedidoRequestDTO.mercadorias()) {
            Mercadoria mercadoria = mercadoriaRepository.findById(dto.idMercadoria())
                    .orElseThrow(() -> new CustomException(ErrorCode.MERCADORIA_NOT_FOUND, dto.idMercadoria()));

            if (mercadoria.getQuantidade() < dto.quantidade()) {
                throw new CustomException(ErrorCode.ESTOQUE_INSUFICIENTE, dto.idMercadoria());
            }

            MercadoriasPedido mercadoriaPedido = new MercadoriasPedido();
            mercadoriaPedido.setPedido(pedido);
            mercadoriaPedido.setMercadoria(mercadoria);
            mercadoriaPedido.setQuantidade(dto.quantidade());
            mercadoriaPedido.setPreco(mercadoria.getPreco() * dto.quantidade());

            mercadorias.add(mercadoriaPedido);

            valorTotal += mercadoriaPedido.getPreco();
        }

        pedido.setValor(valorTotal);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        for (MercadoriasPedido mercadoriaPedido : mercadorias) {
            Mercadoria mercadoria = mercadoriaPedido.getMercadoria();
            mercadoria.setQuantidade(mercadoria.getQuantidade() - mercadoriaPedido.getQuantidade());
            mercadoriaRepository.save(mercadoria);
        }

        for (MercadoriasPedido mercadoriaPedido : mercadorias) {
            mercadoriaPedido.setPedido(pedidoSalvo);
        }

        mercadoriasPedidoRepository.saveAll(mercadorias);

        return pedidoSalvo;
    }
}
