package com.example.orders_service.infrastructure.entity;

import lombok.AllArgsConstructor;

/**
 * Exceção personalizada para quando um produto não for encontrado no sistema.
 */
@AllArgsConstructor // Gera o construtor com o parâmetro "message"
public class ProductNotFoundException extends RuntimeException {

  /**
   * Construtor que recebe uma mensagem personalizada.
   *
   * @param message A mensagem de erro detalhada.
   */
  public ProductNotFoundException(String message) {
    super(message);
  }
}
