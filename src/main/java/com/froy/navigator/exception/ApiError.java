package com.froy.navigator.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Represents a standardized error response for the API.
 * Includes a timestamp, HTTP status, error message, and optional details.
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
