package com.EcommerceProjectModule.UserManagement.Services;

import com.EcommerceProjectModule.UserManagement.Models.Role;
import com.EcommerceProjectModule.UserManagement.Repositories.RoleRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    public Role createRole(String roleName, String desc) {
        Role dbrole = roleRepository.findByRoleName(roleName);
        if(dbrole != null){
            throw new RuntimeException("Role already exists");
        }

        Role role = new Role();
        role.setRoleName(roleName);
        role.setDescription(desc);
        roleRepository.save(role);
        return role;
    }

    public List<Role> getAllRole() {
        List<Role> roles = roleRepository.findAll();
        return roles;
    }
}
