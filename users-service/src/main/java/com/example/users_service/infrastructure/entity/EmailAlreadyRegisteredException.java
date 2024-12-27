package com.example.users_service.infrastructure.entity;

import lombok.AllArgsConstructor;

/**
 * Exceção personalizada para quando o e-mail já estiver registrado no sistema.
 */
@AllArgsConstructor // Gera o construtor com o parâmetro "message"
public class EmailAlreadyRegisteredException extends RuntimeException {

    /**
     * Construtor que recebe uma mensagem personalizada.
     *
     * @param message A mensagem de erro detalhada.
     */
    public EmailAlreadyRegisteredException(String message) {
        super(message);
    }
}
