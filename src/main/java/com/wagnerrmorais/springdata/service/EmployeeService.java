package com.wagnerrmorais.springdata.service;

import com.wagnerrmorais.springdata.orm.Employee;
import com.wagnerrmorais.springdata.orm.Role;
import com.wagnerrmorais.springdata.orm.UnitOfWork;
import com.wagnerrmorais.springdata.repository.EmployeeRepository;
import com.wagnerrmorais.springdata.repository.RoleRepository;
import com.wagnerrmorais.springdata.repository.UnitOfWorkRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class EmployeeService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Boolean system = true;
    private final EmployeeRepository employeeRepository;
    private final UnitOfWorkRepository unitOfWorkRepository;
    private final RoleRepository roleRepository;

    public EmployeeService(EmployeeRepository employeeRepository, UnitOfWorkRepository unitOfWorkRepository, RoleRepository roleRepository) {
        this.employeeRepository = employeeRepository;
        this.unitOfWorkRepository = unitOfWorkRepository;
        this.roleRepository = roleRepository;
    }

    public void init(Scanner scanner) {
        while (system) {
            System.out.println("Which Employee action would you like to execute?");
            System.out.println("0 - Exit");
            System.out.println("1 - Save");
            System.out.println("2 - Update");
            System.out.println("3 - View all");
            System.out.println("4 - Delete");

            int action = scanner.nextInt();

            switch (action) {
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
    }

    private void deleteById(Scanner scanner) {
        System.out.println("Id");
        int id = scanner.nextInt();
        employeeRepository.deleteById(id);
        System.out.println("Deleted!");
    }

    public void save(Scanner scanner) {
        System.out.println("Name: ");
        String name = scanner.next();

        System.out.println("CPF: ");
        String cpf = scanner.next();

        System.out.println("Salary: ");
        Double salary = scanner.nextDouble();

        System.out.println("Starting Date: 'dd/MM/yyyy' ");
        String startingDate = scanner.next();

        System.out.println("Role id: ");
        Integer roleId = scanner.nextInt();

        List<UnitOfWork> unitOfWorks = unit(scanner);

        Employee employee = new Employee();
        employee.setName(name);
        employee.setCpf(cpf);
        employee.setSalary(salary);
        employee.setStartingDate(LocalDate.parse(startingDate, formatter));
        Optional<Role> role = roleRepository.findById(roleId);
        employee.setRole(role.get());
        employee.setWorkUnities(unitOfWorks);

        employeeRepository.save(employee);
        System.out.println("Salvo");
    }

    public void update(Scanner scanner) {
        System.out.println("Id: ");
        Integer id = scanner.nextInt();

        System.out.println("Name: ");
        String name = scanner.next();

        System.out.println("CPF: ");
        String cpf = scanner.next();

        System.out.println("Salary: ");
        Double salary = scanner.nextDouble();

        System.out.println("Starting Date: ");
        String startingDate = scanner.next();

        System.out.println("Role id: ");
        Integer roleId = scanner.nextInt();

        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setCpf(cpf);
        employee.setSalary(salary);
        employee.setStartingDate(LocalDate.parse(startingDate, formatter));
        Optional<Role> role = roleRepository.findById(roleId);
        employee.setRole(role.get());

        employeeRepository.save(employee);
        System.out.println("Alterado");
    }

    public void findAll() {
        Iterable<Employee> funcionarios = employeeRepository.findAll();
        funcionarios.forEach(funcionario -> System.out.println(funcionario));
        system = false;
    }

    private List<UnitOfWork> unit(Scanner scanner) {
        Boolean isTrue = true;
        List<UnitOfWork> unitOfWorks = new ArrayList<>();

        while (isTrue) {
            System.out.println("Unit ID: (To exit, type 0)");
            Integer unitId = scanner.nextInt();

            if (unitId != 0) {
                Optional<UnitOfWork> unitOfWork = unitOfWorkRepository.findById(unitId);
                unitOfWorks.add(unitOfWork.get());
                return unitOfWorks;
            } else {
                isTrue = false;
            }
        }

        return unitOfWorks;
    }

}
