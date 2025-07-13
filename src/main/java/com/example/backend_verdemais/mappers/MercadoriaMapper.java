package com.example.backend_verdemais.mappers;

import com.example.backend_verdemais.dto.MercadoriaRequestDTO;
import com.example.backend_verdemais.dto.MercadoriaResponseDTO;
import com.example.backend_verdemais.entities.Mercadoria;

import static java.util.Objects.isNull;

public class MercadoriaMapper {

    public static MercadoriaResponseDTO paraResponseDTO(Mercadoria mercadoria) {
        if (mercadoria == null) {
            return null;
        }
        return new MercadoriaResponseDTO(
                mercadoria.getId(),
                mercadoria.getNome(),
                mercadoria.getPreco(),
                mercadoria.getQuantidade(),
                mercadoria.getHabilitado(),
                mercadoria.getUsuario().getNome()
        );
    }
}
