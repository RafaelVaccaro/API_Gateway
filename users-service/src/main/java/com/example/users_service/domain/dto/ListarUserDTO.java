package com.example.users_service.domain.dto;

import com.example.users_service.infrastructure.entity.User;

public record ListarUserDTO(Long id, String nome, String email, Boolean status) {

    public ListarUserDTO(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getStatus()
        );
    }
}
