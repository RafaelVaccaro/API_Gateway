package com.example.orders_service.infrastructure.entity;

import com.example.orders_service.domain.dto.RegistroOrderItemDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

/**
 * Representa um item dentro de um pedido (Order).
 */
@Table(name = "order_items")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private Order order;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    /**
     * Construtor que inicializa os campos com base no DTO de registro.
     *
     * @param registroOrderItemDTO DTO com as informações do item de pedido.
     */
    public OrderItem(RegistroOrderItemDTO registroOrderItemDTO) {
        this.productId = registroOrderItemDTO.productId();
        this.quantity = registroOrderItemDTO.quantity();
    }
}
