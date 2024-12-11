package com.example.users_service.controller;

import com.example.users_service.domain.dto.RegistroUserDTO;
import com.example.users_service.domain.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @Transactional
    public void regestrar(@RequestBody @Valid RegistroUserDTO registroUserDTO) {
        userService.registrar(registroUserDTO);
    }

}
