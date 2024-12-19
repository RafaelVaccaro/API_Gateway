package com.example.orders_service.domain.dto;

import com.example.orders_service.infrastructure.entity.Order;
import com.example.orders_service.infrastructure.entity.Status;

import java.time.LocalDateTime;

public record ListarOrderDTO(
        Long id,
        Long userId,
        Double totalPrice,
        Status status,
        LocalDateTime createdAt
) {
    public ListarOrderDTO(Order order) {
        this(order.getId(), order.getUserId(), order.getTotalPrice()  , order.getStatus(), order.getCreatedAt());
    }
}
