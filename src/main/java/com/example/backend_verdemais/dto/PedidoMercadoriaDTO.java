package com.example.backend_verdemais.dto;

public record PedidoMercadoriaDTO(
        Long pedidoId,
        Long mercadoriaId,
        int quantidade,
        double precoUnitario
) {}

