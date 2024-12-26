package com.example.orders_service.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Representa um pedido feito por um usuário.
 */
@Table(name = "orders")
@Entity(name = "Order")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItem> orderItems;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Construtor para criar um pedido com os itens associados.
     *
     * @param userId     O ID do usuário que fez o pedido.
     * @param totalPrice O preço total do pedido.
     * @param orderItems A lista de itens do pedido.
     */
    public Order(Long userId, Double totalPrice, List<OrderItem> orderItems) {
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;

        // Associa os itens ao pedido
        if (orderItems != null) {
            orderItems.forEach(item -> item.setOrder(this));
        }
    }
}
