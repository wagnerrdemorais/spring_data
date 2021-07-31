package com.wagnerrmorais.springdata.service;

import com.wagnerrmorais.springdata.orm.Employee;
import com.wagnerrmorais.springdata.repository.EmployeeRepository;
import com.wagnerrmorais.springdata.specification.EmployeeSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class EmployeeDynamicReport {

    private final EmployeeRepository employeeRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public EmployeeDynamicReport(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void init(Scanner scanner) {
        System.out.println("Name: ");
        String name = scanner.next();
        if(name.equalsIgnoreCase("null")){
            name = null;
        }

        System.out.println("Cpf: ");
        String cpf = scanner.next();
        if(cpf.equalsIgnoreCase("null")){
            cpf = null;
        }

        System.out.println("Salary: ");
        Double salary = scanner.nextDouble();
        if(salary == 0){
            salary = null;
        }

        System.out.println("StartingDate : ");
        String date = scanner.next();

        LocalDate startingDate;

        if(date.equalsIgnoreCase("null")){
            startingDate = null;
        }else{
            startingDate = LocalDate.parse(date, formatter);
        }

        List<Employee> employees = employeeRepository
            .findAll(Specification.where(EmployeeSpecification.name(name))
                .or(Specification.where(EmployeeSpecification.cpf(cpf)))
                .or(Specification.where(EmployeeSpecification.salary(salary)))
                .or(Specification.where(EmployeeSpecification.startingDate(startingDate)))
            );

        employees.forEach(System.out::println);
    }
}
