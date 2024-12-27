package com.example.orders_service.domain.dto;

import com.example.orders_service.infrastructure.entity.Order;
import java.time.LocalDateTime;

/**
 * DTO para listar informações de um pedido.
 */
public record ListarOrderDTO(
        Long id,
        Long userId,
        Double totalPrice,
        LocalDateTime createdAt
) {

    /**
     * Construtor que converte uma entidade Order para o DTO.
     */
    public ListarOrderDTO(Order order) {
        this(order.getId(), order.getUserId(), order.getTotalPrice(), order.getCreatedAt());
    }
}
