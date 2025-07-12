package com.example.backend_verdemais.exception;

import com.example.backend_verdemais.exception.CustomException;
import com.example.backend_verdemais.exception.GlobalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Exceção genérica
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<GlobalResponse> handleCustomException(CustomException ex) {
        GlobalResponse response = new GlobalResponse(ex.getErrorMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Exceção genérica para erros inesperados
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponse> handleGenericException(Exception ex) {
        GlobalResponse response = new GlobalResponse("Erro inesperado: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
