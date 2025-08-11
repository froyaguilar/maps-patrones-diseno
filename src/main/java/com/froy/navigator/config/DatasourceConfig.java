package com.froy.navigator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configuration class for JPA repositories.
 * Enables JPA repositories and specifies the base package where repositories are located.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.froy.navigator.repository")
public class DatasourceConfig {
    // This class primarily serves to enable JPA repositories.
    // DataSource configuration is handled via application.yml and application-prod.yml
}
