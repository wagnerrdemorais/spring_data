package com.wagnerrmorais.springdata.orm;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "unit_of_work")
public class UnitOfWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private String address;
    @ManyToMany(mappedBy = "workUnities", fetch = FetchType.EAGER)
    private List<Employee> employees;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "id: " + id + " employees:  " + employees;
    }
}
