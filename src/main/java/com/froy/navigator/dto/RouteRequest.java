package com.froy.navigator.dto;

import com.froy.navigator.model.TransportMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Objeto de Transferencia de Datos para solicitar una planificación de ruta.
 * Contiene el origen, destino y modo de transporte.
 */
public record RouteRequest(
        @NotNull @Valid
        @Schema(description = "Punto geográfico de origen")
        GeoPoint origin,

        @NotNull @Valid
        @Schema(description = "Punto geográfico de destino")
        GeoPoint destination,

        @NotNull(message = "El modo de transporte no puede ser nulo")
        @Schema(description = "Modo de transporte. Valores permitidos: CAR, BIKE, MOTORCYCLE", example = "CAR")
        TransportMode mode
) {
}
