package com.mamata.employee_service.controller;

import com.mamata.employee_service.dto.EmployeeRequestDto;
import com.mamata.employee_service.dto.EmployeeResponseDto;
import com.mamata.employee_service.entity.Employee;
import com.mamata.employee_service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/save")
    private EmployeeResponseDto saveEmployee(@RequestBody EmployeeRequestDto employeeRequestDto){
        return employeeService.saveEmployee(employeeRequestDto);
    }

    @GetMapping
    public Page<EmployeeResponseDto> getEmployee(@RequestParam(defaultValue = "0")
                                                 int page,
                                                 @RequestParam(defaultValue = "1")
                                                 int size,
                                                 @RequestParam(defaultValue = "id")
                                                 String sortBy,
                                                 @RequestParam(defaultValue = "asc")
                                                 String direction) {
        return employeeService.getAllEmployee(page, size, sortBy, direction);
    }

    @GetMapping("/{id}")
    public EmployeeResponseDto getEmployeeById(@PathVariable Long id){
        return employeeService.getEmpById(id);
    }

    @PutMapping("/{id}")
    public EmployeeResponseDto updateEmployeeById(@PathVariable Long id, @RequestBody EmployeeRequestDto employeeRequestDto){
        return employeeService.updateEmployee(id,employeeRequestDto);
    }
}
