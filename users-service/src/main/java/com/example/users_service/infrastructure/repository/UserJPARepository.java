package com.example.users_service.infrastructure.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.example.users_service.domain.dto.ListarUserDTO;
import com.example.users_service.infrastructure.entity.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface do repositório responsável por realizar operações de persistência na tabela de usuários.
 * Estende JpaRepository para herdar os métodos padrões de manipulação de dados.
 */
@Repository // Indica que esta interface é um componente do Spring e será usada como um repositório de dados.
public interface UserJPARepository extends JpaRepository<User, Long> {

    /**
     * Verifica se existe algum usuário cadastrado com o email especificado.
     *
     * @param email Email a ser verificado.
     * @return true se o email já existir, false caso contrário.
     */
    boolean existsByEmail(String email);
}
