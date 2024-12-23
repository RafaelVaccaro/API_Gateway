package com.example.users_service.domain.dto;

import com.example.users_service.infrastructure.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RegistroUserDTO(

        String name,

        @Email
        String email
) {
        public static RegistroUserDTO toDTO(User user) {
                return RegistroUserDTO.builder().name(user.getName()).email(user.getEmail()).build();
        }
}

