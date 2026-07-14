package com.mamata.employee_service.controller;

import com.mamata.employee_service.dto.EmployeeRequestDto;
import com.mamata.employee_service.dto.EmployeeResponseDto;
import com.mamata.employee_service.dto.EmployeeSearchRequest;
import com.mamata.employee_service.entity.Employee;
import com.mamata.employee_service.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private ResponseEntity<EmployeeResponseDto> saveEmployee(@Valid @RequestBody EmployeeRequestDto employeeRequestDto){
        EmployeeResponseDto employeeResponseDto = employeeService.saveEmployee(employeeRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeResponseDto);
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
    public ResponseEntity<EmployeeResponseDto> updateEmployeeById(@PathVariable Long id, @RequestBody EmployeeRequestDto employeeRequestDto){
        EmployeeResponseDto responseDto= employeeService.updateEmployee(id,employeeRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public Page<EmployeeResponseDto> searchEmployee(EmployeeSearchRequest employeeSearchRequest,
                                                    @PageableDefault(
                                                            size=5,
                                                            sort="id"
                                                    )
                                                    Pageable pagable)
    {
       return employeeService.searchEmployee(employeeSearchRequest,pagable);
    }


}
