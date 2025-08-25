package com.froy.navigator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Manejador global de excepciones para la API REST.
 * Centraliza la lógica de manejo de errores, asegurando que todas las respuestas de error
 * de la API sigan un formato consistente y predecible (ApiError).
 * Utiliza la anotación @RestControllerAdvice para aplicarse a todos los @RestController.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja la BusinessException personalizada y retorna un estado BAD_REQUEST.
     *
     * @param ex Excepción BusinessException lanzada.
     * @param request Petición web durante la cual ocurrió la excepción.
     * @return ResponseEntity con ApiError y estado BAD_REQUEST.
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
     * Maneja la NotFoundException personalizada y retorna un estado NOT_FOUND.
     *
     * @param ex Excepción NotFoundException lanzada.
     * @param request Petición web durante la cual ocurrió la excepción.
     * @return ResponseEntity con ApiError y estado NOT_FOUND.
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
     * Maneja los errores de validación provocados por las anotaciones @Valid y @Validated.
     * Extrae los mensajes de error de MethodArgumentNotValidException y retorna BAD_REQUEST.
     *
     * @param ex Excepción MethodArgumentNotValidException lanzada.
     * @param request Petición web durante la cual ocurrió la excepción.
     * @return ResponseEntity con ApiError y detalles de validación.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(java.util.stream.Collectors.joining("; ")); // Une todos los errores de campo en un solo string.

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Error de validación",
                errors + " - " + request.getDescription(false)
        );
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    /**
     * Captura cualquier otra excepción inesperada y retorna INTERNAL_SERVER_ERROR.
     * Este es un manejador de "último recurso" para evitar que las trazas de pila (stack traces)
     * se filtren al cliente. Registra el error y devuelve una respuesta genérica de error 500.
     * 
     * @param ex Excepción inesperada lanzada.
     * @param request Petición web durante la cual ocurrió la excepción.
     * @return ResponseEntity con ApiError y estado INTERNAL_SERVER_ERROR.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllUncaughtException(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocurrió un error inesperado",
                ex.getLocalizedMessage() + " - " + request.getDescription(false)
        );
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
