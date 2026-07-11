package com.mamata.employee_service.dto;

import lombok.Data;

@Data
public class EmployeeSearchRequest{

    private String name;

    private String department;

    private String email;

    private Double minSalary;

    private Double maxSalary;

    // getters and setters
}