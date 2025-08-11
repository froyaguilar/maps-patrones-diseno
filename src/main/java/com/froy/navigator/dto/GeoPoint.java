package com.froy.navigator.dto;

import com.froy.navigator.validation.GeoCoordinates;

/**
 * Representa un punto geográfico con latitud y longitud.
 * Se utiliza en el DTO RouteRequest.
 */
public record GeoPoint(
        @GeoCoordinates(message = "Latitud inválida") double lat,
        @GeoCoordinates(message = "Longitud inválida") double lon
) {
}
