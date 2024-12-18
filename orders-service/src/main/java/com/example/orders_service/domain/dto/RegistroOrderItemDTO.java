package com.example.orders_service.domain.dto;

import com.example.orders_service.infrastructure.entity.Order;

public record RegistroOrderItemDTO(

        Long productId,
        Integer quantity,
        Double price
) {
}
