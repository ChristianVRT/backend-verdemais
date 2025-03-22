package com.example.backend_verdemais.dto;

import com.example.backend_verdemais.entities.Usuario;

import java.util.List;

public record PedidoDTO(
        Long dataPedido,
        List<MercadoriasPedidoDTO> mercadorias
) {}
