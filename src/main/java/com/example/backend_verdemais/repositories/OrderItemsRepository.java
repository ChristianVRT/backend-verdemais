package com.example.backend_verdemais.repositories;

import com.example.backend_verdemais.entities.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
}
