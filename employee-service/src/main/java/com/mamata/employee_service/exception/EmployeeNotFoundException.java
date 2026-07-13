package com.mamata.employee_service.exception;

import java.net.http.HttpRequest;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(String message){
        super(message);
    }
}
