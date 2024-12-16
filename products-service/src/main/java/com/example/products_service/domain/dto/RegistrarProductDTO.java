package com.example.products_service.domain.dto;

public record RegistrarProductDTO(

        String name,
        String description,
        Double price,
        Integer stock
) {
}
