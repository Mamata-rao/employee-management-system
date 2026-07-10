package com.mamata.employee_service.mapper;

import com.mamata.employee_service.dto.EmployeeRequestDto;
import com.mamata.employee_service.dto.EmployeeResponseDto;
import com.mamata.employee_service.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    private EmployeeMapper() {
    }

    public static Employee toEntity(EmployeeRequestDto dto){
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setSalary(dto.getSalary());
        employee.setDepartment(dto.getDepartment());
        employee.setEmail(dto.getEmail());
        return employee;
    }

    public static EmployeeResponseDto toDto(Employee employee){
        EmployeeResponseDto dto = new EmployeeResponseDto();

        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(employee.getDepartment());
        dto.setSalary(employee.getSalary());

        return dto;

    }
}
