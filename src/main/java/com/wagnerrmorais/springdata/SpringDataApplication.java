package com.wagnerrmorais.springdata;

import com.wagnerrmorais.springdata.orm.Role;
import com.wagnerrmorais.springdata.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    private Boolean system = true;

    private final RoleService service;

    public SpringDataApplication(RoleService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while(system){
            System.out.println("Which action would you like to execute?");
            System.out.println("0 - Exit");
            System.out.println("1 - Role");

            int action = scanner.nextInt();
            if(action == 1) {
                service.init(scanner);
            } else {
                system = false;
            }
        }

        Role role = new Role();
        role.setDescription("Worker");

    }
}
