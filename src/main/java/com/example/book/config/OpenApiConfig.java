package com.example.book.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Book Management"
                ),
                description = "OpenApi documentation for Frontend",
                title = "Book Project Api",
                version = "1.0",
                termsOfService = "Terms of Service"
        )
)
public class OpenApiConfig {
}
