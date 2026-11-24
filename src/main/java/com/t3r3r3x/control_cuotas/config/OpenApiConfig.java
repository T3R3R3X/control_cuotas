package com.t3r3r3x.control_cuotas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI personalizarOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión")
                        .version("1.0")
                        .description("Documentación REST con OpenAPI y Swagger UI"));
    }
}
