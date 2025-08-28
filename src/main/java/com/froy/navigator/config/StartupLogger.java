package com.froy.navigator.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Component that logs the Swagger UI URL and available controllers to the console on application startup.
 * This component is disabled in the 'prod' profile to avoid exposing endpoints in production logs.
 */
@Component
@Profile("!prod")
public class StartupLogger implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(StartupLogger.class);

    private final ApplicationContext context;
    private final String serverPort;
    private final String contextPath;

    public StartupLogger(ApplicationContext context,
                         @Value("${server.port:8080}") String serverPort,
                         @Value("${server.servlet.context-path:}") String contextPath) {
        this.context = context;
        this.serverPort = serverPort;
        this.contextPath = contextPath;
    }

    @Override
    public void run(String... args) {
        String swaggerUiUrl = String.format("http://localhost:%s%s/swagger-ui.html", serverPort, contextPath);

        log.info("====================================================================");
        log.info("Swagger UI available at: {}", swaggerUiUrl);
        log.info("Available Controllers and Endpoints:");

        RequestMappingHandlerMapping mapping = context.getBean(RequestMappingHandlerMapping.class);
        mapping.getHandlerMethods().forEach((info, method) -> {
            Class<?> controller = method.getBeanType();
            // Add a check to avoid NullPointerException on handlers without a path (e.g., BasicErrorController)
            if (info.getPatternsCondition() != null && !controller.equals(BasicErrorController.class)) {
                String path = info.getPatternsCondition().toString();
                String controllerName = controller.getSimpleName();
                log.info("  - Controller: {} -> Path: {}", controllerName, path);
            }
        });
        log.info("====================================================================");
    }
}
