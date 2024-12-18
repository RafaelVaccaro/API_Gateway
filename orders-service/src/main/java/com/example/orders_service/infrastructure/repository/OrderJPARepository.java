package com.example.orders_service.infrastructure.repository;

import com.example.orders_service.infrastructure.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJPARepository extends JpaRepository<Order, Long> {
}
