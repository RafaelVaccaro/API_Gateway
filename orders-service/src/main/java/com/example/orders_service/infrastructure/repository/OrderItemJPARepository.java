package com.example.orders_service.infrastructure.repository;

import com.example.orders_service.infrastructure.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório JPA para operações de CRUD na entidade Item do Pedido.
 * Fornece métodos para acessar e manipular os dados dos itens dos pedidos.
 */
public interface OrderItemJPARepository extends JpaRepository<OrderItem, Long> {
}
