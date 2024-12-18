package com.example.orders_service.infrastructure.entity;
import com.example.orders_service.domain.dto.RegistroOrderDTO;
import com.example.orders_service.domain.dto.RegistroOrderItemDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.PROCESSANDO; // Valor padrão definido na coluna

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    public Order(Long userId, Double totalPrice, List<OrderItem> orderItems) {
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;

        if (orderItems != null) {
            for (OrderItem item : orderItems) {
                item.setOrder(this); // Associa os itens ao pedido
            }
        }
    }
}

