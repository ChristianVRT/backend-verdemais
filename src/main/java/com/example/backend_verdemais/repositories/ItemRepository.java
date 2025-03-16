package com.example.backend_verdemais.repositories;

import com.example.backend_verdemais.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
