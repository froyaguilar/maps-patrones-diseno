package com.froy.navigator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Clase de configuración para los repositorios JPA.
 * Habilita los repositorios y especifica el paquete base donde se encuentran.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.froy.navigator.repository")
public class DatasourceConfig {
    // Esta clase solo habilita los repositorios JPA.
    // La configuración del DataSource se maneja en application.yml, application-prod.yml, application-dev.yml
}
