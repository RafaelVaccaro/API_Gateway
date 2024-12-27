package com.example.products_service.infrastructure.entity;

import lombok.AllArgsConstructor;

/**
 * Exceção personalizada para quando o estoque de um produto for insuficiente.
 */
@AllArgsConstructor // Gera o construtor com o parâmetro "message"
public class InsufficientStockException extends RuntimeException {

    /**
     * Construtor que recebe uma mensagem personalizada.
     *
     * @param message A mensagem de erro detalhada.
     */
    public InsufficientStockException(String message) {
        super(message);
    }
}
