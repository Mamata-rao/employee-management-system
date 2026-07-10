package com.mamata.employee_service.dto;

import lombok.Data;

@Data
public class EmployeeResponseDto {
    private Long id;

    private String name;

    private String email;

    private String department;

    private Double salary;

}
