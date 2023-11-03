package com.employeeManagement.user.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Jean de Dieu Nshimyumukiza",
                        email = "jeandedieu2030@gmail.com",
                        url = "https://github.com/jodosjodos"
                ),
                description = "Welcome to the Swagger documentation for a cutting-edge Spring Boot 3 and Spring Security 6 project. This project is designed to shine a spotlight on authentication and authorization, providing you with robust error handling, email notifications for account creation, and secure user management via JWT token-based security.",
                title = "🚀 Spring Boot 3 & Spring Security 6: Authentication & Authorization",
                version = "1.0",
                license = @License(
                        name = "Connect on LinkedIn",
                        url = "https://www.linkedin.com/in/jean-de-dieu-nshimyumukiza-97b315259/"
                ),
                termsOfService = "Feel free to clone and adapt this code for your own Spring Boot 3 project."
        ),
        servers = {
                @Server(
                        description = "Local Development Environment",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Production Environment on GitHub",
                        url = "https://github.com/jodosjodos"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "Secure your API with a JWT token for user authentication. please provide token for access all endpoints that require to provide token in header one at time .",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfiguration {
}
