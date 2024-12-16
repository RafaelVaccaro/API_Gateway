package com.example.products_service.domain.dto;

import com.example.products_service.infrastructure.entity.Product;
import jakarta.persistence.Column;
import lombok.Builder;

@Builder
public record DetalhamentoProductDTO(
       Long id,
       String name,
       String description,
       Double price,
       Integer stock
) {
    public static DetalhamentoProductDTO toDto(Product product) {
        return DetalhamentoProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock()).build();
    }
}
