package com.example.orders_service.domain.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * DTO usado para registrar um pedido.
 */
public record RegistroOrderDTO(

        // Identificador do usuário, não pode ser nulo.
        @NotNull Long userId,

        // Lista de itens do pedido, não pode ser nula.
        @NotNull List<RegistroOrderItemDTO> orderItems
) {
}
