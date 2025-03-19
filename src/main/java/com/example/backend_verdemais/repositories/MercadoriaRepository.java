package com.example.backend_verdemais.repositories;

import com.example.backend_verdemais.entities.Mercadoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MercadoriaRepository extends JpaRepository<Mercadoria, Long> {
    Optional<Mercadoria> findById(Long id);
}
