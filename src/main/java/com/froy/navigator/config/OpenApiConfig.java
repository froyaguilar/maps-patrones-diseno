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
 * Configuración de la documentación OpenAPI/Swagger para la aplicación.
 * Define la información básica que será mostrada en la interfaz de Swagger UI
 * de forma programática para mayor flexibilidad.
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
     * Crea un grupo de API para la versión 1.
     * GroupedOpenApi permite segmentar la documentación en la UI de Swagger.
     * Esto es útil para versionar APIs o agrupar endpoints por funcionalidad.
     *
     * @return un Grupo de OpenAPI para todos los endpoints bajo /api/v1.
     */
    @Bean
    public GroupedOpenApi publicApiV1() {
        return GroupedOpenApi.builder()
                .group("Rutas-v1") // Nombre que aparecerá en el desplegable de Swagger UI
                .pathsToMatch("/api/v1/**") // Patrón para incluir endpoints. '**' significa cualquier sub-ruta.
                .build();
    }

    /**
     * Bean que personaliza la información general de la API mostrada en Swagger UI.
     * Utiliza los valores inyectados desde los archivos application.yml.
     *
     * @return un objeto OpenAPI configurado.
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
