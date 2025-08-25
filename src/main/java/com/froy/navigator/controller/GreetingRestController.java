package com.froy.navigator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para obtener un mensaje de bienvenida.
 * Este es un endpoint simple para verificar que la configuración de perfiles
 * de la aplicación está funcionando correctamente.
 */
@RestController
@RequestMapping(ApiConstants.GREETING_BASE_PATH) // Path base: /api/v1/greeting
@Tag(name = "Mensaje de Bienvenida", description = "Operaciones para obtener mensajes de bienvenida")
public class GreetingRestController {

    // Inyecta el valor de la propiedad 'mensaje.bienvenida'
    // desde el archivo de propiedades activo (application.yml, application-dev.yml, etc.).
    @Value("${mensaje.bienvenida}")
    private String greetingMessage;

    /**
     * Devuelve el mensaje de bienvenida configurado para el perfil activo.
     *
     * @return ResponseEntity con el mensaje de bienvenida y estado 200 OK.
     */
    @GetMapping // Se mapea a la raíz del controlador: /api/v1/greeting
    @Operation(summary = "Obtener mensaje de bienvenida", description = "Devuelve un mensaje de bienvenida que varía según el perfil de Spring activo.")
    @ApiResponse(responseCode = "200", description = "Mensaje obtenido correctamente", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Hola desde el ambiente DEFAULT")))
    public ResponseEntity<String> getGreeting() {
        // En lugar de devolver el String directamente, lo envolvemos en un ResponseEntity.
        // Esto nos da más control. ResponseEntity.ok() es un atajo para crear
        // una respuesta con estado 200 OK y el cuerpo proporcionado.
        // Es funcionalmente equivalente a `return greetingMessage;` en este caso,
        // pero es una práctica más explícita.
        return ResponseEntity.ok(greetingMessage);
    }
}