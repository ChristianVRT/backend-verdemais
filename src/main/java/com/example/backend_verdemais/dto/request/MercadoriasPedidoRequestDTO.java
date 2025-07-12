package com.example.backend_verdemais.dto.request;

import com.example.backend_verdemais.entities.MercadoriasPedido;

public record MercadoriasPedidoRequestDTO(
        Long idMercadoria,
        int quantidade
) {
    public static MercadoriasPedidoRequestDTO fromEntity(MercadoriasPedido mercadoriasPedido) {
        return new MercadoriasPedidoRequestDTO(
                mercadoriasPedido.getMercadoria().getId(),
                mercadoriasPedido.getQuantidade()
        );
    }
}
