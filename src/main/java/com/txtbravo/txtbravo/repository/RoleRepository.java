package com.txtbravo.txtbravo.repository;

import com.txtbravo.txtbravo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface RoleRepository extends JpaRepository<Role, Integer>
{
    Role findByRole(String role);
}