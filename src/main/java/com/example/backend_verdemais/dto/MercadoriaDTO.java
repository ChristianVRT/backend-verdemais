package com.example.backend_verdemais.dto;

public record MercadoriaDTO(
        Long id,
        String nome,
        double preco,
        Boolean habilitado,
        String Usuario
) {}
