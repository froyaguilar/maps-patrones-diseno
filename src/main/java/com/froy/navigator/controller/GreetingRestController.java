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
 * REST controller for getting a welcome message.
 * This is a simple endpoint to verify that the application's profile configuration
 * is working correctly.
 */
@RestController
@RequestMapping(ApiConstantsRequestMapping.GREETINGRESTCONTROLLER_GREETING_BASE_PATH) // Base path: /api/v1/greeting
@Tag(name = "Welcome Message", description = "Operations to get welcome messages")
public class GreetingRestController {

    // Injects the value of the 'mensaje.bienvenida' property
    // from the active properties file (application.yml, application-dev.yml, etc.).
    @Value("${mensaje.bienvenida}")
    private String greetingMessage;

    /**
     * Returns the welcome message configured for the active profile.
     *
     * @return ResponseEntity with the welcome message and 200 OK status.
     */
    @GetMapping // Mapped to the controller's root: /api/v1/greeting
    @Operation(summary = "Get welcome message", description = "Returns a welcome message that varies depending on the active Spring profile.")
    @ApiResponse(responseCode = "200", description = "Message obtained successfully", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Hello from the DEFAULT environment")))
    public ResponseEntity<String> getGreeting() {
        // Instead of returning the String directly, we wrap it in a ResponseEntity.
        // This gives us more control. ResponseEntity.ok() is a shortcut to create
        // a response with a 200 OK status and the provided body.
        // It is functionally equivalent to `return greetingMessage;` in this case,
        // but it is a more explicit practice.
        return ResponseEntity.ok(greetingMessage);
    }
}