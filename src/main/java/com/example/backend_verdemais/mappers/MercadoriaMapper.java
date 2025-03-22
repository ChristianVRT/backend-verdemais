package com.example.backend_verdemais.mappers;

import com.example.backend_verdemais.dto.MercadoriaDTO;
import com.example.backend_verdemais.entities.Mercadoria;

import static java.util.Objects.isNull;

public class MercadoriaMapper {

    public static MercadoriaDTO paraDTO(Mercadoria mercadoria) {
        if (isNull(mercadoria)) {
            return null;
        }
        return new MercadoriaDTO(
                mercadoria.getId(),
                mercadoria.getNome(),
                mercadoria.getPreco(),
                mercadoria.getHabilitado(),
                mercadoria.getUsuario()
        );
    }
}
