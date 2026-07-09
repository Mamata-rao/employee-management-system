package com.mamata.employee_service.service;

import com.mamata.employee_service.entity.Employee;
import com.mamata.employee_service.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployee();

    Employee getEmpById(Long id);

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);
}
