package com.wagnerrmorais.springdata;

import com.wagnerrmorais.springdata.orm.Role;
import com.wagnerrmorais.springdata.service.EmployeeService;
import com.wagnerrmorais.springdata.service.RoleService;
import com.wagnerrmorais.springdata.service.UnitOfWorkService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    private Boolean system = true;

    private final RoleService roleService;
    private final UnitOfWorkService unitOfWorkService;
    private final EmployeeService employeeService;

    public SpringDataApplication(RoleService roleService, UnitOfWorkService unitOfWorkService, EmployeeService employeeService) {
        this.roleService = roleService;
        this.unitOfWorkService = unitOfWorkService;
        this.employeeService = employeeService;
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
            System.out.println("2 - UnitOfWork");
            System.out.println("3 - Employee");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    roleService.init(scanner);
                    break;
                case 2:
                    unitOfWorkService.init(scanner);
                    break;
                case 3:
                    employeeService.init(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }

        Role role = new Role();
        role.setDescription("Worker");

    }
}
