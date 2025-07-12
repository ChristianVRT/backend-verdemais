package com.example.backend_verdemais.dto;

import com.example.backend_verdemais.entities.Usuario;

public record  PedidoDTO(
        Long id,
        Usuario cliente,
        Long data_pedido,
        Double valor,
        String status
) {}