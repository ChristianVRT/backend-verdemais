package com.example.backend_verdemais.dto.request;

import java.util.List;

public record PedidoRequestDTO(
        Long dataPedido,
        List<MercadoriasPedidoRequestDTO> mercadorias
) {}
