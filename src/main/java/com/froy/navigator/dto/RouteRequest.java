package com.froy.navigator.dto;

import com.froy.navigator.validation.SupportedMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Objeto de Transferencia de Datos para solicitar una planificación de ruta.
 * Contiene el origen, destino y modo de transporte.
 */
public record RouteRequest(
        @NotNull @Valid GeoPoint origin,
        @NotNull @Valid GeoPoint destination,
        @SupportedMode String mode
) {
}
