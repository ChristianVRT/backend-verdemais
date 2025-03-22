package com.example.backend_verdemais.services;

import com.example.backend_verdemais.dto.PedidoDTO;
import com.example.backend_verdemais.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<PedidoDTO> getAllPedidos() {return pedidoRepository.findAllPedidos();}
}
