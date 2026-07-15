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
import org.mockito.ArgumentCaptor;
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
    public void shouldReturnEmpById() {
        Employee employee = new Employee("Mamata",
                "mam@gmail.com",
                2L,
                "IT",
                3453.0);
        when(employeeRepository.findById(2L)).thenReturn(Optional.of(employee));
        EmployeeResponseDto response = employeeService.getEmpById(2L);

        assertNotNull(response);
        verify(employeeRepository).findById(2L);
    }

    @Test
    public void shouldEmployeeUpdateSuccessfully() {
        Employee employee = new Employee();
        employee.setId(2L);
        employee.setName("Old Name");
        employee.setEmail("old@gmail.com");
        employee.setDepartment("HR");
        employee.setSalary(50000.0);

        EmployeeRequestDto request = new EmployeeRequestDto();
        request.setName("Mamata Rao");
        request.setEmail("mamata@gmail.com");
        request.setDepartment("IT");
        request.setSalary(85000.0);

        when(employeeRepository.findById(2L)).thenReturn(Optional.of(employee));

        // when(employeeRepository.save(employee)).thenReturn(employee);
        when(employeeRepository.save(any(Employee.class)))
                .thenAnswer(invocation -> (Employee) invocation.getArgument(0));//gives actual object passed to Mock method

        EmployeeResponseDto response = employeeService.updateEmployee(2L, request);

        assertNotNull(response);
        assertNotNull(response);
        assertEquals("Mamata Rao", response.getName());
        assertEquals("mamata@gmail.com", response.getEmail());
        assertEquals("IT", response.getDepartment());
        assertEquals(85000.0, response.getSalary());
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    public void shouldDeleteEmployee() {

        Employee employee = new Employee();
        employee.setId(1L);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        employeeService.deleteEmployee(1L);
        verify(employeeRepository).findById(1L);
        verify(employeeRepository).delete(employee);

    }

    @Test
    public void shouldThrowExceptionWhenDeleteFails() {
        Employee employee = new Employee();
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.deleteEmployee(1L)
        );

        assertEquals("Employee not found", exception.getMessage());
        verify(employeeRepository,never()).delete(employee);
    }

    @Test
    public void shouldThrowExceptionWhileDeletingEmployee() {
        when(employeeRepository.findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.deleteEmployee(1L)
        );
        verify(employeeRepository, never()).deleteById(any());//if the employee isn't found, delete() should never be called.
    }

    @Test
    public void shouldThrowExceptionWhenUpdatingNonExistingEmployee() {
        EmployeeRequestDto requestDto = new EmployeeRequestDto();
        requestDto.setName("Mamata");

        when(employeeRepository.findById(100L))
                .thenReturn(Optional.empty());

        assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.updateEmployee(100L, requestDto)
        );

        verify(employeeRepository).findById(100L);
        verify(employeeRepository, never()).save(any());

    }

    @Test
    void shouldUpdateEmployeeSuccessfully() {

        Employee employee = new Employee();
        employee.setId(2L);
        employee.setName("Old Name");
        employee.setEmail("old@gmail.com");
        employee.setDepartment("HR");
        employee.setSalary(50000.0);

        EmployeeRequestDto request = new EmployeeRequestDto();
        request.setName("Mamata Rao");
        request.setEmail("mamata@gmail.com");
        request.setDepartment("IT");
        request.setSalary(85000.0);

        when(employeeRepository.findById(2L))
                .thenReturn(Optional.of(employee));

        when(employeeRepository.save(any(Employee.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        EmployeeResponseDto response =
                employeeService.updateEmployee(2L, request);

        ArgumentCaptor<Employee> captor =
                ArgumentCaptor.forClass(Employee.class);//Captures the actual object passed,It is extremely useful whenever you pass complex objects to another component.
        //Suppose if we pass existEmployee.setSalary(0.0) instead of existEmployee.setSalary(employee.getSalary());
        verify(employeeRepository).save(captor.capture());

        Employee savedEmployee = captor.getValue();

        assertEquals("Mamata Rao", savedEmployee.getName());
        assertEquals("mamata@gmail.com", savedEmployee.getEmail());
        assertEquals("IT", savedEmployee.getDepartment());
        assertEquals(85000.0, savedEmployee.getSalary());

        assertEquals("Mamata Rao", response.getName());
    }


}
