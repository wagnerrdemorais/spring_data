package com.wagnerrmorais.springdata.repository;

import com.wagnerrmorais.springdata.orm.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    List<Employee> findByName(String name);

}
