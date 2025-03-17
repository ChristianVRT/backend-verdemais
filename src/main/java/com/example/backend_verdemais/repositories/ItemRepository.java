package com.example.backend_verdemais.repositories;

import com.example.backend_verdemais.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findById(String id);
}
