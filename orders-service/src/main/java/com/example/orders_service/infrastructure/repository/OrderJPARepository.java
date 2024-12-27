package com.example.orders_service.infrastructure.repository;

import com.example.orders_service.domain.dto.ListarOrderDTO;
import com.example.orders_service.infrastructure.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface do repositório responsável por realizar operações de persistência na tabela de pedidos.
 * Estende JpaRepository para herdar os métodos padrões de manipulação de dados.
 */
@Repository // Indica que esta interface é um componente do Spring e será usada como um repositório de dados.
public interface OrderJPARepository extends JpaRepository<Order, Long> {

    /**
     * Recupera os pedidos associados a um usuário específico, com paginação.
     *
     * @param id o ID do usuário cujos pedidos estão sendo recuperados.
     * @param pageable a configuração de paginação.
     * @return página de pedidos do usuário especificado.
     */
    Page<Order> findByUserId(Long id, Pageable pageable);
}
