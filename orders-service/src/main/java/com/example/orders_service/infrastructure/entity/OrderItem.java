package com.example.orders_service.infrastructure.entity;

import com.example.orders_service.domain.dto.RegistroOrderItemDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade que representa um item de produto no banco de dados.
 * Mapeada com a tabela "order_items".
 */
@Table(name = "order_items") // Define o nome da tabela no banco de dados.
@Entity(name = "OrderItem") // Define o nome da entidade para o Hibernate
@Data // Gera getters, setters, toString, equals, hashCode e construtor padrão automaticamente.
@NoArgsConstructor // Gera um construtor sem argumentos.
@AllArgsConstructor // Gera um construtor com todos os argumentos.
@EqualsAndHashCode(of = "id") // Gera métodos equals e hashCode baseados no campo "id".
public class OrderItem {

    @Id // Marca o campo id como a chave primária.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define a geração automática do ID
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
     * Construtor para criar um item com os dados do DTO.
     *
     * @param dados Dados para criar um novo item (RegistroOrderItemDTO).
     */
    public OrderItem(RegistroOrderItemDTO dados) {
        this.productId = dados.productId();
        this.quantity = dados.quantity();
    }
}
