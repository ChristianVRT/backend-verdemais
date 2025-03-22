package com.example.backend_verdemais.dto;


public record UsuarioDTO(
        String nome,
        String email,
        Boolean habilitado
) {}
