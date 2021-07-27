package com.wagnerrmorais.springdata.service;

import com.wagnerrmorais.springdata.orm.Role;
import com.wagnerrmorais.springdata.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void init(Scanner scanner){
        save(scanner);
    }

    public void save(Scanner scanner) {
        System.out.println("Role description:");
        String description = scanner.next();
        Role role = new Role();
        role.setDescription(description);
        roleRepository.save(role);
        System.out.println("Saved!");
    }

}
