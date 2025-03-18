package com.example.backend_verdemais.mappers;

import com.example.backend_verdemais.dto.MercadoriaDTO;
import com.example.backend_verdemais.entities.Item;

import static java.util.Objects.isNull;

public class MercadoriaMapper {

    public static MercadoriaDTO paraDTO(Item item) {
        if (isNull(item)) {
            return null;
        }
        return new MercadoriaDTO(
                item.getId(),
                item.getNome(),
                item.getPreco(),
                item.getHabilitado(),
                item.getUser().getName()
        );
    }
}
