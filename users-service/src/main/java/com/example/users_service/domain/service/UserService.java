package com.example.users_service.domain.service;

import com.example.users_service.domain.dto.ListarUserDTO;
import com.example.users_service.domain.dto.RegistroUserDTO;
import com.example.users_service.infrastructure.entity.User;
import com.example.users_service.infrastructure.repository.UserJPARepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJPARepository userRepository;

    public User registrar(RegistroUserDTO registroUserDTO) {
        if (userRepository.existsByEmail(registroUserDTO.email())) {
            throw new IllegalArgumentException("E-mail já está registrado.");
        }
        return userRepository.save(new User(registroUserDTO));
    }

    public Page<ListarUserDTO> listar(Pageable pageable) {
        return userRepository.findAllByStatusTrue(pageable).map(ListarUserDTO::new);
    }


    public RegistroUserDTO atualizar(@Valid RegistroUserDTO registroUserDTO, Long id) {
        User user = userRepository.getReferenceById(id);
        user.atualizarinfo(registroUserDTO);

        return RegistroUserDTO.toDTO(user);
    }

    public void desativar(Long id) {
        User user = userRepository.getReferenceById(id);
        user.desativar();
    }
}
