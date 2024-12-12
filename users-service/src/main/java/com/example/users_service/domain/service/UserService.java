package com.example.users_service.domain.service;

import com.example.users_service.domain.dto.RegistroUserDTO;
import com.example.users_service.infrastructure.entity.User;
import com.example.users_service.infrastructure.repository.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJPARepository userRepository;

    public void registrar(RegistroUserDTO registroUserDTO) {
        if (userRepository.existsByEmail(registroUserDTO.email())) {
            throw new IllegalArgumentException("E-mail já está registrado.");
        }
        userRepository.save(new User(registroUserDTO));
    }

}
