package com.example.backend_verdemais.dto;

public record MercadoriaRequestDTO(
        String nome,
        double preco,
        int quantidade,
        Boolean habilitado
) {}
