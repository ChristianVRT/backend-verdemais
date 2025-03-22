package com.example.backend_verdemais.dto;


public record UsuarioDTO(
        Long id,
        String nome,
        String email,
        Boolean habilitado
) {}
