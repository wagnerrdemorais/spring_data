package com.wagnerrmorais.springdata.repository;

import com.wagnerrmorais.springdata.orm.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    List<Employee> findByName(String name);

//    List<Employee> findByNameAndSalaryGreaterThanAndStartingDate(String name, Double salary, LocalDate date);

    @Query("SELECT e FROM Employee e WHERE e.name = :name AND e.salary >= :salary AND e.startingDate = :date")
    List<Employee> findNameSalaryGreaterStartingDate(String name, Double salary, LocalDate date);

    @Query(value = "SELECT * FROM employees e WHERE e.starting_date >= :date",
            nativeQuery = true)
    List<Employee> findStargingDateGT(LocalDate date);

}
