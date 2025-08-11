package com.froy.navigator.strategy;

import com.froy.navigator.dto.RouteRequest;
import com.froy.navigator.dto.RouteResponse;

/**
 * Interface for route planning strategies.
 * Implements the Strategy pattern, allowing different algorithms for route calculation
 * based on the mode of transport.
 */
public interface RouteStrategy {

    /**
     * Computes a route based on the given request.
     *
     * @param request The route request containing origin, destination, and mode.
     * @return A RouteResponse containing the calculated distance, duration, steps, and mode.
     */
    RouteResponse compute(RouteRequest request);

    /**
     * Returns the mode of transport handled by this strategy.
     *
     * @return A string representing the transport mode (e.g., "CAR", "BIKE", "MOTORCYCLE").
     */
    String mode();
}
