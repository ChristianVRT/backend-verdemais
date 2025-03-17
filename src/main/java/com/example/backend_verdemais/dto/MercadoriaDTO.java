package com.example.backend_verdemais.dto;

public record MercadoriaDTO(
        String id,
        String nome,
        double preco,
        Boolean habilitado,
        String username
) {}
