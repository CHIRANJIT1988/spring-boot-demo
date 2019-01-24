package com.txtbravo.txtbravo.dao;

import com.txtbravo.txtbravo.model.Role;
import com.txtbravo.txtbravo.repository.RoleRepository;
import com.txtbravo.txtbravo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService
{

    @Autowired
    RoleRepository roleJpaRepository;


    @Override
    public Role findByRole(String role)
    {
        return roleJpaRepository.findByRole(role);
    }
}
