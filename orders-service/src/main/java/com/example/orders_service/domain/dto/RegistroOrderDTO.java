package com.example.orders_service.domain.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * DTO usado para registrar um pedido.
 */
public record RegistroOrderDTO(
        Long userId,
        List<RegistroOrderItemDTO> orderItems
) {
}
