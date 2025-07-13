package com.example.backend_verdemais.dto;

import com.example.backend_verdemais.entities.MercadoriasPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PedidoRequestDTO {
    private List<MercadoriasPedido> mercadorias;

}
