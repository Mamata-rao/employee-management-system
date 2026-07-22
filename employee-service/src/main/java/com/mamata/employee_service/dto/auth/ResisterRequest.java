package com.mamata.employee_service.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResisterRequest {
    @Schema(example = "bulli")
    @NotBlank(message = "Username is required")
    private String username;

    @Schema(example = "bulli@example.com")
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    private String email;

    @Schema(example = "password123")
    @NotBlank(message = "Password is required")
    private String password;
}
