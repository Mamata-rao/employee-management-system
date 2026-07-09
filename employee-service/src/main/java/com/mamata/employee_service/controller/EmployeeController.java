package com.mamata.employee_service.controller;

import com.mamata.employee_service.entity.Employee;
import com.mamata.employee_service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private Employee saveEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeService.getEmpById(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployeeById(@PathVariable Long id, @RequestBody Employee employee){
        return employeeService.updateEmployee(id,employee);
    }
}
