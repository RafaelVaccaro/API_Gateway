package com.example.users_service.infrastructure.entity;

import com.example.users_service.domain.dto.RegistroUserDTO;
import jakarta.persistence.*;
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
    private boolean status;

    public User(RegistroUserDTO dados) {
        this.name = dados.name();
        this.email = dados.email();
        this.status = true;  // Status iniciado como ativo (true)
    }
}
