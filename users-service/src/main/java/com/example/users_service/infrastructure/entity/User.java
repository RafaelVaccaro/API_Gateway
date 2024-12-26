package com.example.users_service.infrastructure.entity;

import com.example.users_service.domain.dto.RegistroUserDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

/**
 * Entidade que representa o usuário no banco de dados.
 * Mapeada com a tabela "users", que armazena as informações dos usuários.
 */
@Table(name = "users") // Define o nome da tabela no banco de dados.
@Entity(name = "User") // Define o nome da entidade, que será usada pelo JPA para mapear os dados da tabela "users".
@Getter // Gera automaticamente os métodos de acesso (getters).
@Setter // Gera automaticamente os métodos de modificação (setters).
@NoArgsConstructor // Gera um construtor sem argumentos.
@AllArgsConstructor // Gera um construtor com todos os argumentos.
@EqualsAndHashCode(of = "id") // Gera métodos equals e hashCode baseados no campo "id".
public class User {

    @Id // Marca o campo id como a chave primária.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define que o valor de id será gerado automaticamente pelo banco de dados.
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

    /**
     * Atualiza as informações do usuário com os dados do DTO,
     * somente campos não nulos no DTO serão atualizados.
     * @param registroUserDTO DTO contendo as informações a serem atualizadas.
     */
    public void atualizarinfo(@Valid RegistroUserDTO registroUserDTO) {
        // Atualiza o nome, se não for nulo.
        if (registroUserDTO.name() != null)
            this.name = registroUserDTO.name();
        // Atualiza o e-mail, se não for nulo.
        if (registroUserDTO.email() != null)
            this.email = registroUserDTO.email();
    }
}
