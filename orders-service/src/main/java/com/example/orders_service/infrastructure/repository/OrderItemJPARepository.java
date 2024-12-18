package com.example.orders_service.infrastructure.repository;

import com.example.orders_service.infrastructure.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJPARepository extends JpaRepository<OrderItem, Long> {
}
