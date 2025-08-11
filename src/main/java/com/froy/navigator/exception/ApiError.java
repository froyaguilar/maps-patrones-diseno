package com.froy.navigator.exception;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Representa una respuesta de error estandarizada para la API.
 * Incluye marca de tiempo, estado HTTP, mensaje de error y detalles opcionales.
 */
public record ApiError(
        @Schema(description = "Fecha y hora del error", example = "2025-08-10T14:00:00Z")
        LocalDateTime timestamp,

        @Schema(description = "Código de estado HTTP", example = "BAD_REQUEST")
        HttpStatus status,

        @Schema(description = "Mensaje descriptivo del error", example = "Error de validación")
        String message,

        @Schema(
                description = "Detalle adicional sobre el error",
                example = "origin.lat: Latitud inválida; mode: Debe ser 'driving', 'walking' o 'bicycling'"
        )
        String details
) {
    public ApiError(HttpStatus status, String message, String details) {
        this(LocalDateTime.now(), status, message, details);
    }
}
