package com.example.users_service.infrastructure.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.example.users_service.domain.dto.ListarUserDTO;
import com.example.users_service.infrastructure.entity.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJPARepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

}
