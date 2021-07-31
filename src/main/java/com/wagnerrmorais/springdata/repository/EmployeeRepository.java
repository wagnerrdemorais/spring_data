package com.wagnerrmorais.springdata.repository;

import com.wagnerrmorais.springdata.orm.Employee;
import com.wagnerrmorais.springdata.orm.EmployeeProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

    List<Employee> findByName(String name);

    @Query("SELECT e FROM Employee e WHERE e.name = :name AND e.salary >= :salary AND e.startingDate = :date")
    List<Employee> findNameSalaryGreaterStartingDate(String name, Double salary, LocalDate date);

    @Query(value = "SELECT * FROM employees e WHERE e.starting_date >= :date",
            nativeQuery = true)
    List<Employee> findStargingDateGT(LocalDate date);

    @Query(value = "SELECT e.id, e.name, e.salary FROM employees e", nativeQuery = true)
    List<EmployeeProjection> findEmployeeSalary();

}
