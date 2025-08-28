package com.froy.navigator.config;

import org.springdoc.core.models.GroupedOpenApi;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Configuration for the application's OpenAPI/Swagger documentation.
 * Defines the basic information to be displayed in the Swagger UI
 * programmatically for greater flexibility.
 */
@Configuration
public class OpenApiConfig {

    @Value("${api.info.title:API de Navegación}")
    private String title;

    @Value("${api.info.version:v1.0.0}")
    private String version;

    @Value("${api.info.description:API para planificar rutas en diferentes modos de transporte}")
    private String description;

    @Value("${api.info.contact.name:Equipo de Desarrollo}")
    private String contactName;

    @Value("${api.info.contact.email:soporte@tuempresa.com}")
    private String contactEmail;

    @Value("${api.info.license.name:Apache 2.0}")
    private String licenseName;

    @Value("${api.info.license.url:http://www.apache.org/licenses/LICENSE-2.0.html}")
    private String licenseUrl;

    /**
     * Creates an API group for version 1.
     * GroupedOpenApi allows segmenting the documentation in the Swagger UI.
     * This is useful for versioning APIs or grouping endpoints by functionality.
     *
     * @return an OpenAPI Group for all endpoints under /api/v1.
     */
    @Bean
    public GroupedOpenApi publicApiV1() {
        return GroupedOpenApi.builder()
                .group("Routes-v1") // Name that will appear in the Swagger UI dropdown
                .pathsToMatch("/api/v1/**") // Pattern to include endpoints. '**' means any sub-path.
                .build();
    }

    /**
     * Bean that customizes the general API information displayed in Swagger UI.
     * Uses values injected from the application.yml files.
     *
     * @return a configured OpenAPI object.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title(title)
                        .version(version)
                        .description(description)
                        .contact(new Contact().name(contactName).email(contactEmail))
                        .license(new License().name(licenseName).url(licenseUrl)));
    }
}
