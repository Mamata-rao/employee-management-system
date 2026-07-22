package com.mamata.employee_service;

import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.SecretKey;
import java.util.Base64;

@SpringBootApplication
public class EmployeeServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(EmployeeServiceApplication.class, args);

	/*	SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

		String secret = Base64.getEncoder().encodeToString(key.getEncoded());

		System.out.println(secret);

	 */
	}


}
