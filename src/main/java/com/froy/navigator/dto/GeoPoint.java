package com.froy.navigator.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Representa un punto geográfico con latitud y longitud.
 * Se utiliza en el DTO RouteRequest.
 */
public record GeoPoint(
        @Min(value = -90, message = "La latitud debe ser como mínimo -90")
        @Max(value = 90, message = "La latitud debe ser como máximo 90")
        @Schema(description = "Latitud del punto", example = "20.6736")
        double lat,

        @Min(value = -180, message = "La longitud debe ser como mínimo -180")
        @Max(value = 180, message = "La longitud debe ser como máximo 180")
        @Schema(description = "Longitud del punto", example = "-103.344")
        double lon
) {
}
