package com.mamata.employee_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    public Employee(String name, String email, Long id, String department, Double salary) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.department = department;
        this.salary = salary;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String name;

    private String email;

    private String department;

    private Double salary;

    public Employee() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }




}
