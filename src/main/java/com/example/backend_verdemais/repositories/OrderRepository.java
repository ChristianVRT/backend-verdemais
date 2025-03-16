package com.example.backend_verdemais.repositories;

import com.example.backend_verdemais.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
