package com.example.backend_verdemais.mappers;

import com.example.backend_verdemais.dto.request.UsuarioRequestDTO;
import com.example.backend_verdemais.entities.Usuario;

import static java.util.Objects.isNull;

public class UsuarioMapper {

    public static UsuarioRequestDTO paraDTO(Usuario usuario) {
        if (isNull(usuario)) {
            return null;
        }
        return new UsuarioRequestDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getHabilitado()
        );
    }
}
