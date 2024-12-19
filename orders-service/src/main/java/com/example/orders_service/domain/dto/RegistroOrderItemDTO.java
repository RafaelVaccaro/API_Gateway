package com.example.orders_service.domain.dto;

import com.example.orders_service.infrastructure.entity.Order;
import jakarta.validation.constraints.NotNull;

public record RegistroOrderItemDTO(

        @NotNull Long productId,
        @NotNull Integer quantity
) {
}
