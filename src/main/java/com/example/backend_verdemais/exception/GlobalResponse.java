package com.example.backend_verdemais.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GlobalResponse {
    private String message;
    private int status;
}
