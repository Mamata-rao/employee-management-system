package com.mamata.employee_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                .title("Employee management System")
                .description("Rest api for Employee Management System")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Mamata Rao")
                                .email("mamata.appikonda@gmail.com")));
    }
}
