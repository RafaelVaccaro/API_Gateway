package com.example.orders_service.infrastructure.repository;

import com.example.orders_service.domain.dto.ListarOrderDTO;
import com.example.orders_service.infrastructure.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJPARepository extends JpaRepository<Order, Long> {

}
