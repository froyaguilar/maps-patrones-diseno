package com.froy.navigator.controller;

import com.froy.navigator.dto.RouteRequest;
import com.froy.navigator.dto.RouteResponse;
import com.froy.navigator.exception.ApiError;
import com.froy.navigator.service.RoutePlanner;
import com.froy.navigator.service.auditing.AuditOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST que gestiona las solicitudes de planificación de rutas.
 * Expone un endpoint para planificar rutas utilizando diferentes modos de transporte.
 */
@RestController
@RequestMapping(ApiConstants.ROUTES_BASE_PATH)
@Tag(name = "Rutas", description = "Operaciones para planificar rutas")
public class RouteRestController {

    private final RoutePlanner routePlanner;

    // --- OpenAPI Example Constants ---

    private static final String EXAMPLE_ROUTE_RESPONSE = """
            {
              "distanceKm": 10.5,
              "durationMinutes": 25,
              "steps": [
                "Salga de Av. Vallarta y continúe 300 m",
                "Gire a la izquierda en Calle López Cotilla"
              ],
              "mode": "driving"
            }""";

    private static final String EXAMPLE_VALIDATION_ERROR = """
            {
              "timestamp": "2025-08-10T14:00:00Z",
              "status": "BAD_REQUEST",
              "message": "Error de validación",
              "details": "origin.lat: Latitud inválida; mode: Debe ser 'driving', 'walking' o 'bicycling'"
            }""";

    private static final String EXAMPLE_ROUTE_NOT_FOUND = """
            {
              "timestamp": "2025-08-10T14:01:00Z",
              "status": "NOT_FOUND",
              "message": "No se encontró la ruta",
              "details": "No hay ruta entre el origen y el destino proporcionados"
            }""";

    /**
     * Crea una instancia de RouteRestController con el servicio RoutePlanner.
     *
     * @param routePlanner Servicio responsable de planificar rutas.
     */
    public RouteRestController(RoutePlanner routePlanner) {
        this.routePlanner = routePlanner;
    }

    /**
     * Planifica una ruta basada en el origen, destino y modo de transporte proporcionados.
     * El cuerpo de la petición se valida con Jakarta Bean Validation.
     * Se crea una entrada de auditoría por cada planificación exitosa.
     *
     * @param request Objeto RouteRequest con origen, destino y modo deseado.
     * @return ResponseEntity con los detalles de la ruta calculada.
     */
    @PostMapping(ApiConstants.PLAN_ROUTE_PATH)
    @AuditOperation("Ruta Planificada")
    @Operation(summary = "Planificar una ruta", description = "Calcula una ruta entre dos puntos usando un modo de transporte")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ruta calculada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RouteResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Ruta de ejemplo",
                                            summary = "Respuesta exitosa",
                                            value = EXAMPLE_ROUTE_RESPONSE)
                            }
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud inválida (parámetros incorrectos o incompletos)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Error de validación",
                                            summary = "Solicitud incorrecta",
                                            value = EXAMPLE_VALIDATION_ERROR)
                            }
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró una ruta entre los puntos dados",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Ruta no encontrada",
                                            summary = "Ruta inexistente",
                                            value = EXAMPLE_ROUTE_NOT_FOUND)
                            }
                    )
            )
    })
    public ResponseEntity<RouteResponse> planRoute(@Valid @RequestBody RouteRequest request) {
        RouteResponse response = routePlanner.planRoute(request);
        return ResponseEntity.ok(response);
    }
}
