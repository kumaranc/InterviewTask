package com.task.common.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleById(Integer id) {
        System.out.println("Role Id :::: " + id);
        Optional<Role> rolee = roleRepository.findById(id);
        if(rolee.isPresent()) {
            System.out.println("Got Department Object");
            return rolee.get();
        } else {
            System.out.println("Not Getting Department Object");
            return new Role();
        }
    }
}
