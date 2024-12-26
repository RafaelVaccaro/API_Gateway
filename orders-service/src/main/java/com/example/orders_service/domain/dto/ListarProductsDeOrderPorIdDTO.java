package com.example.orders_service.domain.dto;

import com.example.orders_service.infrastructure.entity.Order;
import com.example.orders_service.infrastructure.entity.OrderItem;
import lombok.Builder;
import java.util.List;

/**
 * DTO para listar os produtos de um pedido por seu ID.
 */
@Builder
public record ListarProductsDeOrderPorIdDTO(

        // Lista de itens do pedido.
        List<OrderItem> orderItems
) {

    /**
     * Método para converter uma entidade Order em um DTO.
     */
    public static ListarProductsDeOrderPorIdDTO toDTO(Order order) {
        return ListarProductsDeOrderPorIdDTO.builder()
                .orderItems(order.getOrderItems()).build();
    }
}
