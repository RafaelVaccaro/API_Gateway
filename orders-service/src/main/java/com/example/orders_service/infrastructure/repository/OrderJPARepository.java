package com.example.orders_service.infrastructure.repository;

import com.example.orders_service.domain.dto.ListarOrderDTO;
import com.example.orders_service.infrastructure.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório JPA para operações de CRUD na entidade Pedido.
 * Proporciona métodos para acessar e manipular os dados da tabela de pedidos.
 */
public interface OrderJPARepository extends JpaRepository<Order, Long> {

    /**
     * Recupera os pedidos associados a um usuário específico, com suporte à paginação.
     *
     * @param id o ID do usuário cujos pedidos estão sendo recuperados.
     * @param pageable a configuração de paginação.
     * @return uma página de pedidos do usuário especificado.
     */
    Page<Order> findByUserId(Long id, Pageable pageable);
}
