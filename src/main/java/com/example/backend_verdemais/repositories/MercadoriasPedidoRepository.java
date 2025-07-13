package com.example.backend_verdemais.repositories;

import com.example.backend_verdemais.entities.MercadoriasPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MercadoriasPedidoRepository extends JpaRepository<MercadoriasPedido, Long> {
    List<MercadoriasPedido> findByPedido_Id(Long pedidoId);
}

