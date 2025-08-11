package com.froy.navigator.dto;

import com.froy.navigator.validation.SupportedMode;
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

        @SupportedMode
        @Schema(description = "Modo de transporte", example = "driving")
        String mode
) {
}
