package com.example.backend_verdemais.dto;

import com.example.backend_verdemais.entities.Usuario;

public record MercadoriaDTO(
        Long id,
        String nome,
        double preco,
        Boolean habilitado,
        Usuario usuario
) {}
