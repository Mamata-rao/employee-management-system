package com.mamata.employee_service.service.auth;

import com.mamata.employee_service.dto.auth.LoginRequest;
import com.mamata.employee_service.dto.auth.LoginResponse;
import com.mamata.employee_service.dto.auth.ResisterRequest;

public interface AuthService {
    void register(ResisterRequest request);

    LoginResponse login(LoginRequest loginRequest);
}
