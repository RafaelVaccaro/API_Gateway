package com.example.orders_service.infrastructure.repository;

import com.example.orders_service.infrastructure.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface do repositório responsável por realizar operações de persistência na tabela de itens de pedido.
 * Estende JpaRepository para herdar os métodos padrões de manipulação de dados.
 */
@Repository // Indica que esta interface é um componente do Spring e será usada como um repositório de dados.
public interface OrderItemJPARepository extends JpaRepository<OrderItem, Long> {
}
