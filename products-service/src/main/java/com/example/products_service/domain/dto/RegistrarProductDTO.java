package com.example.products_service.domain.dto;

import jakarta.validation.constraints.NotNull;

/**
 * DTO utilizado para registrar um novo produto.
 */
public record RegistrarProductDTO(
        String name,
        String description,
        Double price,
        Integer stock
) {
}
