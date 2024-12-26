package com.example.products_service.domain.dto;

import com.example.products_service.infrastructure.entity.Product;

/**
 * DTO utilizado para listar informações de produtos.
 */
public record ListarProductDTO(
        Long id,
        String name,
        String description,
        Double price,
        Integer stock) {

    /**
     * Converte uma entidade Product para o DTO ListarProductDTO.
     *
     * @param product A entidade Product a ser convertida.
     */
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
