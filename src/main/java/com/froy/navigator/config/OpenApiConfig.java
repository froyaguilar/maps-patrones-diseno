package com.froy.navigator.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de la documentación OpenAPI/Swagger para la aplicación.
 * Define información básica que será mostrada en la interfaz de Swagger UI.
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "API de Navegación", version = "v1", description = "API para planificar rutas en diferentes modos de transporte"))
public class OpenApiConfig {
    // La configuración se maneja mediante las anotaciones anteriores.
}
