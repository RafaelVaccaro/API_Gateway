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

@Service // Indica que esta classe é um componente de serviço no Spring, responsável por lógica de negócios.
@RequiredArgsConstructor // Gera um construtor para os atributos finais, facilitando a injeção de dependências.
public class UserService {

    private final UserJPARepository userJPARepository; // Repositório JPA para operações no banco de dados.

    /**
     * Registra um novo usuário.
     *
     * @param registroUserDTO Dados do usuário a ser registrado.
     * @return O usuário salvo no banco de dados.
     * @throws IllegalArgumentException se o email já estiver registrado.
     */
    public User registrar(RegistroUserDTO registroUserDTO) {
        if (userJPARepository.existsByEmail(registroUserDTO.email())) {
            throw new IllegalArgumentException("E-mail já está registrado."); // Exceção para evitar duplicação de email.
        }
        return userJPARepository.save(new User(registroUserDTO)); // Salva o usuário no banco de dados.
    }

    /**
     * Lista os usuários de forma paginada.
     *
     * @param pageable Objeto que contém informações de paginação.
     * @return Uma página de ListarUserDTO contendo os usuários listados.
     */
    public Page<ListarUserDTO> listar(Pageable pageable) {
        return userJPARepository.findAll(pageable) // Busca todos os usuários paginados.
                .map(ListarUserDTO::new); // Converte para o DTO adequado.
    }

    /**
     * Deleta um usuário pelo ID.
     *
     * @param id ID do usuário a ser deletado.
     */
    public void deletarUser(Long id) {
        userJPARepository.deleteById(id); // Remove o usuário do banco de dados.
    }

    /**
     * Valida se um usuário existe pelo ID fornecido.
     *
     * @param id ID do usuário a ser validado.
     * @return true se o usuário existir.
     * @throws Exception se o usuário não for encontrado.
     */
    public boolean validarUserPorId(Long id) throws Exception {
        if (!userJPARepository.existsById(id)) {
            throw new Exception("Usuario nao encontrado"); // Exceção personalizada para ausência de usuário.
        }
        return true; // Retorna true se o usuário existir.
    }
}
