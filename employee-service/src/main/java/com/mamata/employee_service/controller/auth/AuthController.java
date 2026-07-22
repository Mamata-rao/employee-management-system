package com.mamata.employee_service.controller.auth;

import com.mamata.employee_service.dto.auth.LoginRequest;
import com.mamata.employee_service.dto.auth.LoginResponse;
import com.mamata.employee_service.dto.auth.ResisterRequest;
import com.mamata.employee_service.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<String> registerUser(@Valid @RequestBody ResisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("User Resister succesfully");


    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
