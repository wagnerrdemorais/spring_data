package com.wagnerrmorais.springdata.service;

import com.wagnerrmorais.springdata.orm.Role;
import com.wagnerrmorais.springdata.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class RoleService {

    private Boolean system = true;
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void init(Scanner scanner){
        while (system){
            System.out.println("Which Role action would you like to execute?");
            System.out.println("0 - Exit");
            System.out.println("1 - Save");
            System.out.println("2 - Update");
            System.out.println("3 - View all");
            System.out.println("4 - Delete");

            int action = scanner.nextInt();

            switch (action){
                case 0:
                    system = false;
                    break;
                case 1:
                    save(scanner);
                    break;
                case 2:
                    update(scanner);
                    break;
                case 3:
                    findAll();
                    break;
                case 4:
                    deleteById(scanner);
                    break;
                default:
                    system = false;
                    break;
            }

        }

        save(scanner);
    }

    private void deleteById(Scanner scanner) {
        System.out.println("Id");
        int id = scanner.nextInt();
        roleRepository.deleteById(id);
        System.out.println("Deleted!");
    }

    public void save(Scanner scanner) {
        System.out.println("Role description:");
        String description = scanner.next();
        Role role = new Role();
        role.setDescription(description);
        roleRepository.save(role);
        System.out.println("Saved!");
    }

    public void update(Scanner scanner) {
        System.out.println("Id:");
        int id = scanner.nextInt();
        System.out.println("Role description");
        String description = scanner.next();

        Role role = new Role();
        role.setId(id);
        role.setDescription(description);
        roleRepository.save(role);
        System.out.println("Updated!");
    }

    public void findAll() {
        Iterable<Role> roles = roleRepository.findAll();
        roles.forEach(System.out::println);
        system = false;
    }

}
