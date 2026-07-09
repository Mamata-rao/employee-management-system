package com.mamata.employee_service.service.impl;

import com.mamata.employee_service.entity.Employee;
import com.mamata.employee_service.repository.EmployeeRepository;
import com.mamata.employee_service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmpById(Long id) {
        return  employeeRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Employee not found"));
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {

        Employee existEmployee = employeeRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Employee not found"));
        existEmployee.setEmail(employee.getEmail());
        existEmployee.setName(employee.getName());
        existEmployee.setDepartment(employee.getDepartment());
        existEmployee.setSalary(employee.getSalary());
        return employeeRepository.save(existEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
