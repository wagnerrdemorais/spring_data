package com.wagnerrmorais.springdata.repository;

import com.wagnerrmorais.springdata.orm.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
