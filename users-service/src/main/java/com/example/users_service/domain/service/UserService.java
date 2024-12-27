package com.example.users_service.domain.service;

import com.example.users_service.domain.dto.ListarUserDTO;
import com.example.users_service.domain.dto.RegistroUserDTO;
import com.example.users_service.infrastructure.entity.EmailAlreadyRegisteredException;
import com.example.users_service.infrastructure.entity.User;
import com.example.users_service.infrastructure.repository.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Serviço responsável pela lógica de negócios relacionada aos usuários.
 * Contém métodos para registrar, listar, deletar e validar usuários.
 */
@Service // Indica que esta classe é um componente de serviço no Spring, responsável por lógica de negócios.
@RequiredArgsConstructor // Gera um construtor para os atributos finais, facilitando a injeção de dependências.
public class UserService {

    private final UserJPARepository userJPARepository; // Repositório para operações de persistência de usuários.

    /**
     * Registra um novo usuário.
     *
     * @param registroUserDTO DTO contendo os dados do usuário a ser registrado.
     * @return O usuário registrado.
     * @throws EmailAlreadyRegisteredException se o email já estiver registrado.
     */
    public User registrar(RegistroUserDTO registroUserDTO) {
        if (userJPARepository.existsByEmail(registroUserDTO.email())) {
            throw new EmailAlreadyRegisteredException("E-mail já está registrado."); // Exceção personalizada para e-mail duplicado.
        }
        // Salva o usuário no banco de dados.
        return userJPARepository.save(new User(registroUserDTO));
    }

    /**
     * Lista os usuários de forma paginada.
     *
     * @param pageable Objeto que contém informações de paginação.
     * @return Uma página de ListarUserDTO contendo os usuários listados.
     */
    public Page<ListarUserDTO> listar(Pageable pageable) {
        // Busca todos os usuários paginados.
        return userJPARepository.findAll(pageable)
                // Converte para o DTO adequado.
                .map(ListarUserDTO::new);
    }

    /**
     * Deleta um usuário pelo ID.
     *
     * @param id ID do usuário a ser deletado.
     */
    public void deletarUser(Long id) {
        userJPARepository.deleteById(id);
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
