package com.example.backend_verdemais.repositories;

import com.example.backend_verdemais.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
