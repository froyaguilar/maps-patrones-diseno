package com.froy.navigator.dto;

import com.froy.navigator.validation.GeoCoordinates;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Representa un punto geográfico con latitud y longitud.
 * Se utiliza en el DTO RouteRequest.
 */
public record GeoPoint(
        @GeoCoordinates(message = "Latitud inválida")
        @Schema(description = "Latitud del punto", example = "20.6736")
        double lat,

        @GeoCoordinates(message = "Longitud inválida")
        @Schema(description = "Longitud del punto", example = "-103.344")
        double lon
) {
}
