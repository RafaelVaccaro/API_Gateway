package com.example.users_service.domain.dto;

import com.example.users_service.infrastructure.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder // Gera um builder para a classe, facilitando a criação de objetos com um padrão fluente.
public record RegistroUserDTO(

        String name, // Nome do usuário.

        @Email // Valida se o valor do campo está no formato de um endereço de email.
        String email // Email do usuário.
) {
        /**
         * Converte uma entidade User para um DTO RegistroUserDTO.
         *
         * @param user Entidade do usuário que será convertida.
         * @return Uma instância de `RegistroUserDTO` contendo os dados da entidade.
         */
        public static RegistroUserDTO toDTO(User user) {
                return RegistroUserDTO.builder() // Inicia o builder para criar uma instância de RegistroUserDTO.
                        .name(user.getName()) // Define o campo `name` com o valor da entidade.
                        .email(user.getEmail()) // Define o campo `email` com o valor da entidade.
                        .build(); // Constrói o objeto DTO.
        }
}

