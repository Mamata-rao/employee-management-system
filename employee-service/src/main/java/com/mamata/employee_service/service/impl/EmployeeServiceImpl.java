package com.mamata.employee_service.service.impl;

import com.mamata.employee_service.dto.EmployeeRequestDto;
import com.mamata.employee_service.dto.EmployeeResponseDto;
import com.mamata.employee_service.dto.EmployeeSearchRequest;
import com.mamata.employee_service.entity.Employee;
import com.mamata.employee_service.exception.EmployeeNotFoundException;
import com.mamata.employee_service.mapper.EmployeeMapper;
import com.mamata.employee_service.repository.EmployeeRepository;
import com.mamata.employee_service.service.EmployeeService;
import com.mamata.employee_service.specification.EmployeeSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDto saveEmployee(EmployeeRequestDto employeeRequestDto) {
        Employee employee = EmployeeMapper.toEntity(employeeRequestDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.toDto(savedEmployee);
    }

    @Override
    public Page<EmployeeResponseDto> getAllEmployee(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Employee> employeePage = employeeRepository.findAll(pageable);

        return employeePage.map(EmployeeMapper::toDto);
    }

    @Override
    public EmployeeResponseDto getEmpById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id" + id));
        log.info("Employee found by id: {}", id);
        return EmployeeMapper.toDto(employee);
    }

    @Override
    public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto employeeRequestDto) {
        Employee employee = EmployeeMapper.toEntity(employeeRequestDto);
        Employee existEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        existEmployee.setEmail(employee.getEmail());
        existEmployee.setName(employee.getName());
        existEmployee.setDepartment(employee.getDepartment());
        existEmployee.setSalary(employee.getSalary());
        Employee updateEmployee = employeeRepository.save(existEmployee);
        return EmployeeMapper.toDto(updateEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                        .orElseThrow(()->new EmployeeNotFoundException("Employee not found"));
        employeeRepository.delete(employee);
    }

    @Override
    public Page<EmployeeResponseDto> searchEmployee(EmployeeSearchRequest employeeSearchRequest, Pageable pageable) {
        Specification<Employee> specification = EmployeeSpecification.search(employeeSearchRequest);
        return employeeRepository.findAll(specification, pageable)
                .map(EmployeeMapper::toDto);
    }
}
