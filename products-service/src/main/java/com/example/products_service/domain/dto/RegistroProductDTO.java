package com.example.products_service.domain.dto;

public record RegistroProductDTO(
        Long id,
        String name,
        String description,
        Double price,
        Integer stock) {
}
