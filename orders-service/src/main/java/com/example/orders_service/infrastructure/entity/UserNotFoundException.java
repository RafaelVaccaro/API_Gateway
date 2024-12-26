package com.example.orders_service.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Exceção personalizada para quando um usuário não for encontrado no sistema.
 */
@AllArgsConstructor // Gera um construtor com todos os parâmetros
public class UserNotFoundException extends RuntimeException {

  /**
   * Construtor que recebe uma mensagem personalizada.
   *
   * @param message A mensagem de erro detalhada.
   */
  public UserNotFoundException(String message) {
    super(message);
  }
}
