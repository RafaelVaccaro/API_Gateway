package com.example.orders_service.domain.dto;

import com.example.orders_service.infrastructure.entity.Order;
import jakarta.validation.constraints.NotNull;

/**
 * DTO usado para registrar um item no pedido.
 */
public record RegistroOrderItemDTO(
        Long productId,
        Integer quantity
) {
}
