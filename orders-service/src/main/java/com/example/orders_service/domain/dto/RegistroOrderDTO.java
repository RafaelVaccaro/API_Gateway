package com.example.orders_service.domain.dto;

import java.util.List;

public record RegistroOrderDTO(
        Long userId,
        List<RegistroOrderItemDTO> orderItems
) {
}




