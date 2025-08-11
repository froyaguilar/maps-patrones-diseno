package com.froy.navigator.dto;

import java.util.List;

/**
 * Data Transfer Object for the route plan response.
 * Contains the calculated distance, duration, steps, and the mode used.
 */
public record RouteResponse(
        double distanceKm,
        int durationMinutes,
        List<String> steps,
        String mode
) {
}
