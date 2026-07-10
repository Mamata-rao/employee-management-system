package com.mamata.employee_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeRequestDto {
    @NotBlank(message = "Name is requried")
    private String name;
    @Email(message = "Email is requried")
    private String email;

    private String department;
    private Double salary;

}
