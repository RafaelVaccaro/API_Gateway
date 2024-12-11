package com.example.users_service.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record RegistroUserDTO(
        @NotBlank String name,

        @NotBlank
        String email
) {
}
