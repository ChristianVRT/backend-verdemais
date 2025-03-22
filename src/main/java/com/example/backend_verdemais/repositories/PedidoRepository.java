package com.example.backend_verdemais.repositories;

import com.example.backend_verdemais.dto.PedidoDTO;
import com.example.backend_verdemais.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT new com.example.backend_verdemais.dto.PedidoDTO(p.id, p.cliente, p.data_pedido , p.valor, p.status, p.mercadoriasPedidos) FROM Pedido p")
    List<PedidoDTO> findAllPedidos();
}
