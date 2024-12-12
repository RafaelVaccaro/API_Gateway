package com.example.users_service.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegistroUserDTO(
        @NotBlank String name,

        @NotBlank @Email
        String email
) {
}

