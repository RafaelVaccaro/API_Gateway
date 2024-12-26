package com.example.orders_service.domain.dto;

import com.example.orders_service.infrastructure.entity.Order;
import java.time.LocalDateTime;

/**
 * DTO para listar informações de um pedido.
 */
public record ListarOrderDTO(
        Long id,            // ID do pedido
        Long userId,       // ID do usuário associado ao pedido
        Double totalPrice, // Preço total do pedido
        LocalDateTime createdAt // Data de criação do pedido
) {

    /**
     * Construtor que converte uma entidade Order para o DTO.
     */
    public ListarOrderDTO(Order order) {
        this(order.getId(), order.getUserId(), order.getTotalPrice(), order.getCreatedAt());
    }
}
