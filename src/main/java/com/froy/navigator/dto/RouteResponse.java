package com.froy.navigator.dto;

import java.util.List;

/**
 * Objeto de Transferencia de Datos para la respuesta de la planificación de la ruta.
 * Contiene la distancia calculada, duración, pasos y el modo utilizado.
 */
public record RouteResponse(
        double distanceKm,
        int durationMinutes,
        List<String> steps,
        String mode
) {
}
