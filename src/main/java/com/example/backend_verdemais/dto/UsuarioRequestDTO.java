package com.example.backend_verdemais.dto;


public record UsuarioRequestDTO(
        Long id,
        String nome,
        String email,
        Boolean habilitado
) {}
