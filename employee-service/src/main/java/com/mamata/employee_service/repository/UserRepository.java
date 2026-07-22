package com.mamata.employee_service.repository;

import com.mamata.employee_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String userName);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String userName);

    boolean existsByEmail(String email);
}
