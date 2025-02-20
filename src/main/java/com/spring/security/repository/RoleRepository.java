package com.spring.security.repository;

import com.spring.security.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findByRoleName(String roleName);
}
