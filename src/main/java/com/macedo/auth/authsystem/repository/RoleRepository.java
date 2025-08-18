package com.macedo.auth.authsystem.repository;

import com.macedo.auth.authsystem.entity.Role;
import com.macedo.auth.authsystem.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
