package com.froy.navigator.dto;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Objeto de Transferencia de Datos para la respuesta de la planificación de la ruta.
 * Contiene la distancia calculada, duración, pasos y el modo utilizado.
 */
public record RouteResponse(
        @Schema(description = "Distancia total en kilómetros", example = "10.5")
        double distanceKm,

        @Schema(description = "Duración estimada en minutos", example = "25")
        int durationMinutes,

        @Schema(
                description = "Instrucciones paso a paso",
                example = "[\"Salga de Av. Vallarta y continúe 300 m\", \"Gire a la izquierda en Calle López Cotilla\"]"
        )
        List<String> steps,

        @Schema(description = "Modo de transporte usado", example = "driving")
        String mode
) {
}
