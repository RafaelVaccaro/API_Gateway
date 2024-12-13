package com.example.products_service.infrastructure.repository;

import com.example.products_service.infrastructure.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJPARepository extends JpaRepository<Product, Long> {
}
