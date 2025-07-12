package com.example.backend_verdemais.exception;

import lombok.Getter;

public enum ErrorCode {

    MERCADORIA_NOT_FOUND("MERCADORIA_NOT_FOUND", "Mercadoria não encontrada: ID %s"),
    ESTOQUE_INSUFICIENTE("ESTOQUE_INSUFICIENTE", "Estoque insuficiente para a mercadoria: ID %s"),
    PEDIDO_INVALIDO("PEDIDO_INVALIDO", "Pedido inválido: %s");

    @Getter
    private final String code;
    private final String messageTemplate;

    ErrorCode(String code, String messageTemplate) {
        this.code = code;
        this.messageTemplate = messageTemplate;
    }

    public String getMessage(Object... args) {
        return String.format(messageTemplate, args);
    }
}
