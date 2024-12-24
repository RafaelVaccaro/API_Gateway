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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public User registrar(@RequestBody @Valid RegistroUserDTO registroUserDTO) {
        return userService.registrar(registroUserDTO);
    }

    @GetMapping
    public Page<ListarUserDTO> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        return userService.listar(paginacao);
    }

    @PutMapping("/{id}")
    public RegistroUserDTO atualizar(@RequestBody @Valid RegistroUserDTO registroUserDTO, @PathVariable Long id) {
        return userService.atualizar(registroUserDTO, id);
    }

    @DeleteMapping("/{id}")
    public void deletarUser(@PathVariable Long id) {
        userService.deletarUser(id);
    }

    @GetMapping("/{id}")
    public boolean validarUserPorId(@PathVariable Long id) throws Exception {
        return userService.validarUserPorId(id);
    }
}
