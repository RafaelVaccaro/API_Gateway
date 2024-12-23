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

    private final UserJPARepository userJPARepository;

    public User registrar(RegistroUserDTO registroUserDTO) {
        if (userJPARepository.existsByEmail(registroUserDTO.email())) {
            throw new IllegalArgumentException("E-mail já está registrado.");
        }
        return userJPARepository.save(new User(registroUserDTO));
    }

    public Page<ListarUserDTO> listar(Pageable pageable) {
        return userJPARepository.findAll(pageable).map(ListarUserDTO::new);
    }

    public RegistroUserDTO atualizar(@Valid RegistroUserDTO registroUserDTO, Long id) {
        User user = userJPARepository.getReferenceById(id);
        user.atualizarinfo(registroUserDTO);

        return RegistroUserDTO.toDTO(user);
    }

    public void deletarUser(Long id) {
        userJPARepository.deleteById(id);
    }

    public boolean validarUserPorId(Long id) throws Exception {
        if (!userJPARepository.existsById(id)) {
            throw new Exception("Usuario nao encontrado");
        }
        return true; // Retorna true se o usuário existir
    }
}
