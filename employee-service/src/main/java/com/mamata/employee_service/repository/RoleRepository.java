package com.mamata.employee_service.repository;

import com.mamata.employee_service.entity.Role;
import com.mamata.employee_service.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(RoleName name);
}
