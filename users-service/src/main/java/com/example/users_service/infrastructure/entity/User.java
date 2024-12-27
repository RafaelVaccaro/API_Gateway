package com.example.users_service.infrastructure.entity;

import com.example.users_service.domain.dto.RegistroUserDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

/**
 * Entidade que representa um usuário no banco de dados.
 * Mapeada com a tabela "users", que armazena as informações dos usuários.
 */
@Table(name = "users") // Define o nome da tabela no banco de dados.
@Entity(name = "User") // Define o nome da entidade para o Hibernate
@Data // Gera getters, setters, toString, equals, hashCode e construtor padrão automaticamente.
@NoArgsConstructor // Gera um construtor sem argumentos.
@AllArgsConstructor // Gera um construtor com todos os argumentos.
@EqualsAndHashCode(of = "id") // Gera métodos equals e hashCode baseados no campo "id".
public class User {

    @Id // Marca o campo id como a chave primária.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define a geração automática do ID
    private Long id;

    @Column(name = "name", nullable = false) // Define a coluna "name" que não pode ser nula.
    private String name;

    @Column(name = "email", nullable = false, unique = true) // Define a coluna "email" como única e não nula.
    private String email;

    /**
     * Construtor que inicializa um usuário com os dados do DTO.
     * @param dados Dados para criar um novo usuário (RegistroUserDTO).
     */
    public User(RegistroUserDTO dados) {
        this.name = dados.name();
        this.email = dados.email();
    }
}
