package com.froy.navigator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Global exception handler for the REST API.
 * Catches specific exceptions and returns standardized ApiError responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles custom BusinessException and returns a BAD_REQUEST status.
     *
     * @param ex The BusinessException that was thrown.
     * @param request The web request during which the exception occurred.
     * @return A ResponseEntity containing an ApiError with BAD_REQUEST status.
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiError> handleBusinessException(BusinessException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles custom NotFoundException and returns a NOT_FOUND status.
     *
     * @param ex The NotFoundException that was thrown.
     * @param request The web request during which the exception occurred.
     * @return A ResponseEntity containing an ApiError with NOT_FOUND status.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles validation errors caused by @Valid and @Validated annotations.
     * Extracts error messages from MethodArgumentNotValidException and returns a BAD_REQUEST status.
     *
     * @param ex The MethodArgumentNotValidException that was thrown.
     * @param request The web request during which the exception occurred.
     * @return A ResponseEntity containing an ApiError with BAD_REQUEST status and validation details.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(java.util.stream.Collectors.joining("; "));

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Validation Error",
                errors + " - " + request.getDescription(false)
        );
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    /**
     * Catches any other unexpected exceptions and returns an INTERNAL_SERVER_ERROR status.
     *
     * @param ex The unexpected Exception that was thrown.
     * @param request The web request during which the exception occurred.
     * @return A ResponseEntity containing an ApiError with INTERNAL_SERVER_ERROR status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllUncaughtException(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred",
                ex.getLocalizedMessage() + " - " + request.getDescription(false)
        );
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
