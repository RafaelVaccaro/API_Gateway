package com.example.orders_service.domain.dto;

import com.example.orders_service.infrastructure.entity.Order;

import java.time.LocalDateTime;

public record ListarOrderDTO(
        Long id,
        Long userId,
        Double totalPrice,
        LocalDateTime createdAt
) {
    public ListarOrderDTO(Order order) {
        this(order.getId(), order.getUserId(), order.getTotalPrice(), order.getCreatedAt());
    }
}
