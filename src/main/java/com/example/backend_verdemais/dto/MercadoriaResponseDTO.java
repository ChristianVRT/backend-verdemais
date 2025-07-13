package com.example.backend_verdemais.dto;

public record MercadoriaResponseDTO(
        Long id,
        String nome,
        double preco,
        int quantidade,
        Boolean habilitado,
        String nomeUsuario
) {}
