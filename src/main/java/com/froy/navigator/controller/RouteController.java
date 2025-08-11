package com.froy.navigator.controller;

import com.froy.navigator.dto.RouteRequest;
import com.froy.navigator.dto.RouteResponse;
import com.froy.navigator.service.RoutePlanner;
import com.froy.navigator.service.auditing.AuditOperation;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/v1/routes")
@Tag(name = "Rutas", description = "Operaciones para planificar rutas")
public class RouteController {

    private final RoutePlanner routePlanner;

    /**
     * Crea una instancia de RouteController con el servicio RoutePlanner.
     *
     * @param routePlanner Servicio responsable de planificar rutas.
     */
    public RouteController(RoutePlanner routePlanner) {
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
    @PostMapping("/plan")
    @AuditOperation("Ruta Planificada")
    @Operation(summary = "Planificar una ruta", description = "Calcula una ruta entre dos puntos usando un modo de transporte")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ruta calculada correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "No se encontró la ruta")
    })
    public ResponseEntity<RouteResponse> planRoute(@Valid @RequestBody RouteRequest request) {
        RouteResponse response = routePlanner.planRoute(request);
        return ResponseEntity.ok(response);
    }
}
