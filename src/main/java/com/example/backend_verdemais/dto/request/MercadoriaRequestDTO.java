package com.example.backend_verdemais.dto.request;

public record MercadoriaRequestDTO(
        String nome,
        double preco,
        int quantidade,
        Boolean habilitado
) {}
