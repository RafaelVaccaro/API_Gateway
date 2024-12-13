package com.example.users_service.infrastructure.entity;

import com.example.users_service.domain.dto.RegistroUserDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "users")
@Entity(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "status", nullable = false)
    private Boolean status;

    public User(RegistroUserDTO dados) {
        this.name = dados.name();
        this.email = dados.email();
        this.status = true;  // Status iniciado como ativo (true)
    }

    public void atualizarinfo(@Valid RegistroUserDTO registroUserDTO) {
        if (registroUserDTO.name() != null)
            this.name = registroUserDTO.name();
        if (registroUserDTO.email() != null)
            this.email = registroUserDTO.email();
    }

    public void desativar() {
        this.status = false;
}
}