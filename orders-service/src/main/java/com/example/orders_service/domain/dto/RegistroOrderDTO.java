package com.example.orders_service.domain.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RegistroOrderDTO(
        @NotNull Long userId,
        @NotNull List<RegistroOrderItemDTO> orderItems
) {
}




