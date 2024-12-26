package com.example.products_service.domain.dto;

/**
 * DTO utilizado para registrar um novo produto.
 */
public record RegistrarProductDTO(

        /**
         * Nome do produto.
         */
        String name,

        /**
         * Descrição do produto.
         */
        String description,

        /**
         * Preço do produto.
         */
        Double price,

        /**
         * Quantidade em estoque do produto.
         */
        Integer stock
) {
}
