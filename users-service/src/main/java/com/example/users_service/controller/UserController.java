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
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Endpoint para registrar um novo usuário.
     *
     * @param registroUserDTO Dados do usuário a ser registrado.
     * @return O usuário recém-criado.
     */
    @PostMapping
    @Transactional
    public User registrar(@RequestBody @Valid RegistroUserDTO registroUserDTO) {
        return userService.registrar(registroUserDTO);
    }

    /**
     * Endpoint para listar os usuários com paginação.
     *
     * @param paginacao Parâmetro de paginação (tamanho e ordenação dos resultados).
     * @return Página de usuários.
     */
    @GetMapping
    public Page<ListarUserDTO> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        return userService.listar(paginacao);
    }

    /**
     * Endpoint para deletar um usuário.
     *
     * @param id ID do usuário a ser deletado.
     */
    @DeleteMapping("/{id}")
    public void deletarUser(@PathVariable Long id) {
        userService.deletarUser(id);
    }

    /**
     * Endpoint para validar a existência de um usuário pelo ID.
     *
     * @param id ID do usuário a ser verificado.
     * @return true se o usuário existir, caso contrário retorna 404.
     */
    @GetMapping("/{id}")
    public boolean validarUserPorId(@PathVariable Long id) throws Exception {
        return userService.validarUserPorId(id);
    }
}