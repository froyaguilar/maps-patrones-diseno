package com.froy.navigator.dto;

import com.froy.navigator.validation.GeoCoordinates;

/**
 * Represents a geographical point with latitude and longitude.
 * Used in the RouteRequest DTO.
 */
public record GeoPoint(
        @GeoCoordinates(message = "Invalid latitude") double lat,
        @GeoCoordinates(message = "Invalid longitude") double lon
) {
}
