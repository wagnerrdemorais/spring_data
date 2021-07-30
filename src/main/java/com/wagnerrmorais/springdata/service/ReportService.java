package com.wagnerrmorais.springdata.service;

import com.wagnerrmorais.springdata.orm.Employee;
import com.wagnerrmorais.springdata.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class ReportService {

    private Boolean system = true;

    private final EmployeeRepository employeeRepository;

    public ReportService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void init(Scanner scanner) {
        while (system) {
            System.out.println("Which Report action would you like to execute?");
            System.out.println("0 - Exit");
            System.out.println("1 - find employee by name");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    findEmployeeByName(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void findEmployeeByName(Scanner scanner) {
        System.out.println("Name to find: ");
        String name = scanner.next();
        List<Employee> list = employeeRepository.findByName(name);
        list.forEach(System.out::println);
    }

}
