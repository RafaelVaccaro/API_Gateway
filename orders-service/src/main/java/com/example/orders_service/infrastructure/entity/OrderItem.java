package com.example.orders_service.infrastructure.entity;
import com.example.orders_service.domain.dto.RegistroOrderItemDTO;
import jakarta.persistence.*;
import lombok.*;

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

    @Setter
    @ManyToOne // Correção do relacionamento
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // Agora é uma entidade, não um Long

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public OrderItem(RegistroOrderItemDTO registroOrderItemDTO) {
        this.productId = registroOrderItemDTO.productId();
        this.quantity = registroOrderItemDTO.quantity();
    }

}

