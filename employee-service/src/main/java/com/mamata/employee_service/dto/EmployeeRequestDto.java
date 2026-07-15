package com.mamata.employee_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDto {
    @Schema(example = "Mamata Rao")
    @NotBlank(message = "Name is requried")
    private String name;

    @Schema(example = "mam@gmail.com")
    @Email(message = "Email is requried")
    private String email;

    @Schema(example = "Engineering")
    private String department;

    @Schema(example = "850000")
    private Double salary;

}
