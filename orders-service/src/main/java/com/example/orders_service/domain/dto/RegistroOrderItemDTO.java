package com.example.orders_service.domain.dto;

import com.example.orders_service.infrastructure.entity.Order;
import jakarta.validation.constraints.NotNull;

/**
 * DTO usado para registrar um item no pedido.
 */
public record RegistroOrderItemDTO(

        // Identificador do produto, não pode ser nulo.
        @NotNull Long productId,

        // Quantidade do produto, não pode ser nula.
        @NotNull Integer quantity
) {
}
