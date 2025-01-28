package com.example.users_service.domain.dto;

import com.example.users_service.infrastructure.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

/**
 * DTO utilizado para registrar um novo usuário.
 */
@Builder
public record RegistroUserDTO(
        String nome,
        @Email
        String email
) {
        /**
         * Converte uma entidade User para um DTO RegistroUserDTO.
         *
         * @param user Entidade do usuário que será convertida.
         * @return Uma instância de `RegistroUserDTO` contendo os dados da entidade.
         */
        public static RegistroUserDTO toDTO(User user) {
                return RegistroUserDTO.builder() // Inicia o builder para criar uma instância de RegistroUserDTO.
                        .nome(user.getNome())
                        .email(user.getEmail())
                        .build(); // Constrói o objeto DTO.
        }
}

