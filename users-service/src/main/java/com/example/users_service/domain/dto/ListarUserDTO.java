package com.example.users_service.domain.dto;

import com.example.users_service.infrastructure.entity.User;

/**
 * DTO utilizado para listar informações de usuários.
 */
public record ListarUserDTO(
        Long id,
        String nome,
        String email
) {

    /**
     * Construtor que converte uma entidade User em um DTO ListarUserDTO.
     *
     * @param user Entidade do usuário que será convertida.
     */
    public ListarUserDTO(User user) {
        this( // Chama o construtor principal do record passando os valores necessários.
                user.getId(), // Extrai o ID do usuário da entidade.
                user.getNome(), // Extrai o nome do usuário da entidade.
                user.getEmail() // Extrai o email do usuário da entidade.
        );
    }
}