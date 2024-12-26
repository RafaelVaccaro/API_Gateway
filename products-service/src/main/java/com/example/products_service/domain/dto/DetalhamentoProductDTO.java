package com.example.products_service.domain.dto;

import com.example.products_service.infrastructure.entity.Product;
import jakarta.persistence.Column;
import lombok.Builder;

/**
 * DTO utilizado para detalhamento das informações de um produto.
 */
@Builder
public record DetalhamentoProductDTO(
        Long id,
        String name,
        String description,
        Double price,
        Integer stock
) {

    /**
     * Converte uma entidade Product para o DTO DetalhamentoProductDTO.
     *
     * @param product A entidade Product a ser convertida.
     * @return O DTO DetalhamentoProductDTO.
     */
    public static DetalhamentoProductDTO toDto(Product product) {
        return DetalhamentoProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock()).build();
    }
}
