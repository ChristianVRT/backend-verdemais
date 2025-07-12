package com.example.backend_verdemais.repositories;

import com.example.backend_verdemais.dto.request.PedidoRequestDTO;
import com.example.backend_verdemais.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT new com.example.backend_verdemais.dto.request.PedidoRequestDTO(p.data_pedido, p.mercadoriasPedidos) FROM Pedido p")
    List<PedidoRequestDTO> findAllPedidos();
}
