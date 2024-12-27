package com.example.orders_service.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidade que representa um pedido no banco de dados.
 * Mapeada com a tabela "orders".
 */
@Table(name = "orders") // Define o nome da tabela no banco de dados.
@Entity(name = "Order") // Define o nome da entidade para o Hibernate
@Data // Gera getters, setters, toString, equals, hashCode e construtor padrão automaticamente.
@NoArgsConstructor // Gera um construtor sem argumentos.
@AllArgsConstructor // Gera um construtor com todos os argumentos.
@EqualsAndHashCode(of = "id") // Gera métodos equals e hashCode baseados no campo "id".
public class Order {

    @Id // Marca o campo id como a chave primária.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define a geração automática do ID
    private Long id;

    @Column(name = "userId", nullable = false) // Define a coluna 'userId' que não pode ser nula.
    private Long userId;

    @Column(name = "total_price", nullable = false) // Define a coluna 'total_price' que não pode ser nula.
    private Double totalPrice;

    // Define relacionamento OneToMany, mapeado pelo campo "order" na classe OrderItem
    // Propaga todas as operações (persist, merge, remove, etc.) para os itens
    // Remove automaticamente itens órfãos que saírem da lista
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Gerencia a referência JSON para evitar ciclos na serialização
    private List<OrderItem> orderItems;

    @CreationTimestamp // Marca o campo com o timestamp de criação automaticamente
    @Column(name = "created_at", nullable = false, updatable = false) // Define o nome da coluna como "created_at", tornando-a não-nula e não atualizável
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
