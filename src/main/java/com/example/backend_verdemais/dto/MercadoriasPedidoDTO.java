package com.example.backend_verdemais.dto;

import com.example.backend_verdemais.entities.MercadoriasPedido;

public record MercadoriasPedidoDTO(
        Long id,
        Long idPedido,
        Long idMercadoria,
        int quantidade,
        double precoUnitario
) {
    public static MercadoriasPedidoDTO fromEntity(MercadoriasPedido mercadoriasPedido) {
        return new MercadoriasPedidoDTO(
                mercadoriasPedido.getId(),
                mercadoriasPedido.getPedido().getId(),
                mercadoriasPedido.getMercadoria().getId(),
                mercadoriasPedido.getQuantidade(),
                mercadoriasPedido.getPrecoUnitario()
        );
    }
}