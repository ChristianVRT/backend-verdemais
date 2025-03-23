package com.example.backend_verdemais.mappers;

import com.example.backend_verdemais.dto.request.MercadoriaRequestDTO;
import com.example.backend_verdemais.entities.Mercadoria;

import static java.util.Objects.isNull;

public class MercadoriaMapper {

    public static MercadoriaRequestDTO paraDTO(Mercadoria mercadoria) {
        if (isNull(mercadoria)) {
            return null;
        }
        return new MercadoriaRequestDTO(
                mercadoria.getNome(),
                mercadoria.getPreco(),
                mercadoria.getQuantidade(),
                mercadoria.getHabilitado()
        );
    }
}
