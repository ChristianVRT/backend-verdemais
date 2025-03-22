package com.example.backend_verdemais.mappers;

import com.example.backend_verdemais.dto.UsuarioDTO;
import com.example.backend_verdemais.entities.Usuario;

import static java.util.Objects.isNull;

public class UsuarioMapper {

    public static UsuarioDTO paraDTO(Usuario usuario) {
        if (isNull(usuario)) {
            return null;
        }
        return new UsuarioDTO(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getHabilitado()
        );
    }
}
