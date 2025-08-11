package com.froy.navigator.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Componente que registra en consola la URL de Swagger UI y los controladores disponibles al iniciar la aplicación.
 */
@Component
public class StartupLogger implements CommandLineRunner {

    private final ApplicationContext context;

    public StartupLogger(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run(String... args) {
        // URL predeterminada de Swagger UI
        System.out.println("Swagger UI disponible en: http://localhost:8080/swagger-ui.html");
        // Enumerar controladores y sus rutas
        RequestMappingHandlerMapping mapping = context.getBean(RequestMappingHandlerMapping.class);
        mapping.getHandlerMethods().forEach((info, method) -> {
            Class<?> controller = method.getBeanType();
            System.out.println("Controlador: " + controller.getSimpleName() + " – ruta: " + info.getPatternsCondition());
        });
    }
}
