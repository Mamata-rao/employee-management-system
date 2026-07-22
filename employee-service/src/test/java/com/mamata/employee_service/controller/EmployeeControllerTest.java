package com.mamata.employee_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mamata.employee_service.dto.EmployeeRequestDto;
import com.mamata.employee_service.dto.EmployeeResponseDto;
import com.mamata.employee_service.security.jwt.JwtService;
import com.mamata.employee_service.security.service.CustomerUserDetailsService;
import com.mamata.employee_service.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc(addFilters = false)
public class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnEmployeeById() throws Exception {
        EmployeeResponseDto responseDto = new EmployeeResponseDto();
        responseDto.setName("Mamata Rao");
        responseDto.setEmail("mam@gmail.com");
        responseDto.setId(1L);

        when(employeeService.getEmpById(1L)).
                thenReturn(responseDto);

        mockMvc.perform(get("/employee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Mamata Rao"))
                .andExpect(jsonPath("$.email").value("mam@gmail.com"));

    }

    @Test
    void shouldCreateEmployee() throws Exception {
        EmployeeRequestDto request =
                new EmployeeRequestDto();

        request.setName("Mamata Rao");
        request.setEmail("mamata@gmail.com");
        request.setDepartment("IT");
        request.setSalary(85000.0);

        EmployeeResponseDto response =
                new EmployeeResponseDto();

        response.setId(1L);
        response.setName("Mamata Rao");
        when(employeeService.saveEmployee(request))
                .thenReturn(response);
        mockMvc.perform(
                        post("/employee/save")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Mamata Rao"));
    }

    @Test
    void shouldReturnBadRequestForInvalidInput() throws Exception {
        EmployeeRequestDto request =
                new EmployeeRequestDto();

        request.setName("");

        mockMvc.perform(
                        post("/employee/save")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldUpdateEmployee() throws Exception {
        EmployeeRequestDto request =
                new EmployeeRequestDto();

        request.setName("Mamata Rao");
        request.setEmail("mamata@gmail.com");
        request.setDepartment("IT");
        request.setSalary(85000.0);

        EmployeeResponseDto response =
                new EmployeeResponseDto();

        response.setId(2L);
        response.setName("Mamata Rao");
        when(employeeService.updateEmployee(1L, request)).thenReturn(response);

        mockMvc.perform(
                        put("/employee/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                ).andExpect(status().isOk())

                .andExpect(jsonPath("$.name").value("Mamata Rao"));
    }

    @Test
    void shouldDeleteEmployee() throws Exception {

        doNothing().when(employeeService).deleteEmployee(1L);

        mockMvc.perform(delete("/employee/1"))
                .andExpect(status().isNoContent());

        verify(employeeService).deleteEmployee(1L);
    }

}
