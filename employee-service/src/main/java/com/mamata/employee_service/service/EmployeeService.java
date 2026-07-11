package com.mamata.employee_service.service;

import com.mamata.employee_service.dto.EmployeeRequestDto;
import com.mamata.employee_service.dto.EmployeeResponseDto;
import com.mamata.employee_service.dto.EmployeeSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeService {

    EmployeeResponseDto saveEmployee(EmployeeRequestDto employeeRequestDto);

    Page<EmployeeResponseDto> getAllEmployee(int page, int size, String sortBy, String direction);

    EmployeeResponseDto getEmpById(Long id);

    EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto employeeRequestDto);

    void deleteEmployee(Long id);

   Page<EmployeeResponseDto> searchEmployee(EmployeeSearchRequest employeeSearchRequest, Pageable pageable);
}
