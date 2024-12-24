package com.example.products_service.domain.dto;

import com.example.products_service.infrastructure.entity.Product;

public record ListarProductDTO(
        Long id,
        String name,
        String description,
        Double price,
        Integer stock) {

    public ListarProductDTO(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
    }
}
