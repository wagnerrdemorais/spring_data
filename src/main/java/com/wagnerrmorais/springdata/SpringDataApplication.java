package com.wagnerrmorais.springdata;

import com.wagnerrmorais.springdata.orm.Role;
import com.wagnerrmorais.springdata.service.*;
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
    private final ReportService reportService;
    private final EmployeeDynamicReport employeeDynamicReport;

    public SpringDataApplication(RoleService roleService,
                                 UnitOfWorkService unitOfWorkService,
                                 EmployeeService employeeService,
                                 ReportService reportService, EmployeeDynamicReport employeeDynamicReport) {
        this.roleService = roleService;
        this.unitOfWorkService = unitOfWorkService;
        this.employeeService = employeeService;
        this.reportService = reportService;
        this.employeeDynamicReport = employeeDynamicReport;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (system) {
            System.out.println("Which action would you like to execute?");
            System.out.println("0 - Exit");
            System.out.println("1 - Role");
            System.out.println("2 - UnitOfWork");
            System.out.println("3 - Employee");
            System.out.println("4 - Report");
            System.out.println("5 - Employee dynamic report");

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
                case 4:
                    reportService.init(scanner);
                    break;
                case 5:
                    employeeDynamicReport.init(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }
}
