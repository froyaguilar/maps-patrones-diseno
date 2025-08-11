package com.froy.navigator.dto;

import com.froy.navigator.validation.SupportedMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object for requesting a route plan.
 * Contains the origin, destination, and mode of transport.
 */
public record RouteRequest(
        @NotNull @Valid GeoPoint origin,
        @NotNull @Valid GeoPoint destination,
        @SupportedMode String mode
) {
}
