package com.example.users_service.controller;

import com.example.users_service.domain.dto.ListarUserDTO;
import com.example.users_service.domain.dto.RegistroUserDTO;
import com.example.users_service.domain.service.UserService;
import com.example.users_service.infrastructure.entity.User;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
/**
 * Controlador responsável pela gestão de usuários.
 * Exposição de endpoints REST para operações CRUD de usuários.
 */
@RestController // Indica que essa classe é um controlador REST no Spring.
@RequestMapping("/user") // Define a URL base para os endpoints dessa classe.
@RequiredArgsConstructor // Garante que a injeção de dependência será feita automaticamente no construtor.
public class UserController {

    private final UserService userService; // A dependência do serviço que lida com a lógica de usuários.

    /**
     * Endpoint para registrar um novo usuário.
     *
     * @param registroUserDTO Dados do usuário a ser registrado.
     * @return O usuário recém-criado.
     */
    @PostMapping // Define que esse método responderá às requisições POST para a URL '/user'.
    @Transactional // Indica que a operação deve ser realizada dentro de uma transação.
    public User registrar(@RequestBody @Valid RegistroUserDTO registroUserDTO) {
        return userService.registrar(registroUserDTO);
    }

    /**
     * Endpoint para listar os usuários com paginação.
     *
     * @param paginacao Parâmetro de paginação (tamanho e ordenação dos resultados).
     * @return Página de usuários.
     */
    @GetMapping // Define que esse método responderá às requisições GET para a URL '/user'.
    public Page<ListarUserDTO> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        return userService.listar(paginacao);
    }

    /**
     * Endpoint para deletar um usuário.
     *
     * @param id ID do usuário a ser deletado.
     */
    @DeleteMapping("/{id}") // Define que esse método responderá às requisições DELETE para a URL '/user/{id}'.
    public void deletarUser(@PathVariable Long id) {
        userService.deletarUser(id);
    }

    /**
     * Endpoint para validar a existência de um usuário pelo ID.
     *
     * @param id ID do usuário a ser verificado.
     * @return true se o usuário existir, caso contrário retorna 404.
     */
    @GetMapping("/{id}") // Define que esse método responderá às requisições GET para a URL '/user/{id}'.
    public boolean validarUserPorId(@PathVariable Long id) throws Exception {
        return userService.validarUserPorId(id);
    }
}