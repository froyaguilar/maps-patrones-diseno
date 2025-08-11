package com.froy.navigator.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Representa una respuesta de error estandarizada para la API.
 * Incluye marca de tiempo, estado HTTP, mensaje de error y detalles opcionales.
 */
public record ApiError(
        LocalDateTime timestamp,
        HttpStatus status,
        String message,
        String details
) {
    public ApiError(HttpStatus status, String message, String details) {
        this(LocalDateTime.now(), status, message, details);
    }
}
