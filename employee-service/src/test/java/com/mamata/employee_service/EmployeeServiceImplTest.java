package com.mamata.employee_service;

import com.mamata.employee_service.dto.EmployeeRequestDto;
import com.mamata.employee_service.dto.EmployeeResponseDto;
import com.mamata.employee_service.entity.Employee;
import com.mamata.employee_service.exception.EmployeeNotFoundException;
import com.mamata.employee_service.mapper.EmployeeMapper;
import com.mamata.employee_service.repository.EmployeeRepository;
import com.mamata.employee_service.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void shouldEmployeeSaveSuccesful() {
        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto();
        employeeRequestDto.setName("Mamata");
        employeeRequestDto.setSalary(347.0);
        employeeRequestDto.setDepartment("IT");
        employeeRequestDto.setEmail("mamata@gmail.com");

        Employee employee = EmployeeMapper.toEntity(employeeRequestDto);

        when(employeeRepository.save(any(Employee.class)))
                .thenReturn(employee);
        EmployeeResponseDto response = employeeService.saveEmployee(employeeRequestDto);
        employee.setId(2L);
        assertNotNull(response);
        assertEquals("Mamata", response.getName());

    }

    @Test
    public void shouldReturnEmpById(){
        Employee employee =new Employee("Mamata",
                "mam@gmail.com",
                2L,
                "IT",
                3453.0);
        when(employeeRepository.findById(2L)).thenReturn(Optional.of(employee));
        EmployeeResponseDto response = employeeService.getEmpById(2L);

        assertNotNull(response);
        verify(employeeRepository.findById(2L));
    }

    @Test
    public void shouldEmployeeUpdateSuccessfully(){
        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto();
        employeeRequestDto.setName("Mamata");
        employeeRequestDto.setSalary(347.0);
        employeeRequestDto.setDepartment("IT");
        employeeRequestDto.setEmail("mamata@gmail.com");

        Employee employee = EmployeeMapper.toEntity(employeeRequestDto);
        employee.setId(2L);

        when(employeeRepository.findById(2L)).thenReturn(Optional.of(employee));
        employeeRequestDto.setName("Bulli");
        employeeRequestDto.setSalary(3478.0);
        employeeRequestDto.setDepartment("Commerce");
        employeeRequestDto.setEmail("Bulli@gmail.com");
        when(employeeRepository.save(employee)).thenReturn(employee);

        EmployeeResponseDto employeeResponseDto = employeeService.updateEmployee(2L,employeeRequestDto);

        assertNotNull(employeeResponseDto);
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    public void shouldDeleteEmployee(){


        employeeService.deleteEmployee(1L);
        verify(employeeRepository).deleteById(1L);

    }

    @Test
    public void shouldThrowExceptionWhenDeleteFails(){
        doThrow(new RuntimeException("Database error"))
                .when(employeeRepository)
                .deleteById(1L);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                ()->employeeService.deleteEmployee(1L)
        );
        assertEquals("Database error",exception.getMessage());
        verify(employeeRepository).deleteById(1L);
    }

    @Test
    public void shouldThrowExceptionWhileDeletingEmployee(){
        when(employeeRepository.findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(
                EmployeeNotFoundException.class,
                ()->employeeService.deleteEmployee(1L)
        );
        verify(employeeRepository, never()).deleteById(any());//if the employee isn't found, delete() should never be called.
    }


}
