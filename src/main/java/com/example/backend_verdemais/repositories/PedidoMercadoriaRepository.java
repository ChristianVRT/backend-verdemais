package com.example.backend_verdemais.repositories;

import com.example.backend_verdemais.entities.PedidoMercadoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoMercadoriaRepository extends JpaRepository<PedidoMercadoria, Long> {
    List<PedidoMercadoria> findByPedido_IdPedido(Long idPedido);
}
