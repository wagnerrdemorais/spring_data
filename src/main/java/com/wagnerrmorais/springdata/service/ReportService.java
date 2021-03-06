package com.wagnerrmorais.springdata.service;

import com.wagnerrmorais.springdata.orm.Employee;
import com.wagnerrmorais.springdata.orm.EmployeeProjection;
import com.wagnerrmorais.springdata.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class ReportService {

    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final EmployeeRepository employeeRepository;

    public ReportService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void init(Scanner scanner) {
        while (system) {
            System.out.println("Which Report action would you like to execute?");
            System.out.println("0 - Exit");
            System.out.println("1 - find employee by name");
            System.out.println("2 - find employee by SalaryGT and StartingDate: ");
            System.out.println("3 - find employee by startingDate gt: ");
            System.out.println("4 - find employees with salary: ");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    findEmployeeByName(scanner);
                    break;
                case 2:
                    findEmployeeSalaryGTAndStartingDate(scanner);
                    break;
                case 3:
                    findEmployeeByStartingDateGT(scanner);
                    break;
                case 4:
                    findEmployeesSalary();
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

    private void findEmployeeSalaryGTAndStartingDate(Scanner scanner) {
        System.out.println("Name: ");
        String name = scanner.next();

        System.out.println("Starting date: ");
        String startingDate = scanner.next();
        LocalDate date = LocalDate.parse(startingDate, formatter);

        System.out.println("Salary: ");
        Double salary = scanner.nextDouble();

        List<Employee> list = employeeRepository.findNameSalaryGreaterStartingDate(name, salary, date);
        list.forEach(System.out::println);
    }

    private void findEmployeeByStartingDateGT(Scanner scanner){
        System.out.println("Which date ?: 'dd/MM/yyyy' ");
        String startingDate = scanner.next();
        LocalDate date = LocalDate.parse(startingDate, formatter);
        List<Employee> employees = employeeRepository.findStargingDateGT(date);
        employees.forEach(System.out::println);
    }

    private void findEmployeesSalary() {
        List<EmployeeProjection> list = employeeRepository.findEmployeeSalary();
        list.forEach(employeeProjection -> System.out.println("Employee: Id: " + employeeProjection.getId()
        + " | name: " + employeeProjection.getName()
        + " | salary: " + employeeProjection.getSalary())
        );
    }

}
