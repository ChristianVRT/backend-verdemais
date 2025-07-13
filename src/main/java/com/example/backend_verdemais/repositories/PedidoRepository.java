package com.example.backend_verdemais.repositories;

import com.example.backend_verdemais.dto.PedidoRequestDTO;
import com.example.backend_verdemais.entities.PedidoMercadoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoMercadoria, Long> {

    @Query("SELECT new com.example.backend_verdemais.dto.PedidoRequestDTO(p.mercadoriasPedidos) FROM PedidoMercadoria p")
    List<PedidoRequestDTO> findAllPedidos();
}
