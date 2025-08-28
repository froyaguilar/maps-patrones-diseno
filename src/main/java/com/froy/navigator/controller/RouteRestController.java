package com.froy.navigator.controller;

import com.froy.navigator.dto.RouteRequest;
import com.froy.navigator.dto.RouteResponse;
import com.froy.navigator.exception.ApiError;
import com.froy.navigator.service.RoutePlanner;
import com.froy.navigator.service.auditing.AuditOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller that manages route planning requests.
 * Exposes an endpoint to plan routes using different transport modes.
 */
@RestController
@RequestMapping(ApiConstantsRequestMapping.ROUTERESTCONTROLLER_RM_ROUTES_BASE_PATH)
@Tag(name = "Routes", description = "Operations for planning routes")
public class RouteRestController {

    private final RoutePlanner routePlanner;

    // --- OpenAPI Example Constants ---

    private static final String EXAMPLE_ROUTE_RESPONSE = """
            {
              "distanceKm": 10.5,
              "durationMinutes": 25,
              "steps": [
                "Exit Av. Vallarta and continue for 300 m",
                "Turn left on Calle López Cotilla"
              ],
              "mode": "driving"
            }""";

    private static final String EXAMPLE_VALIDATION_ERROR = """
            {
              "timestamp": "2025-08-10T14:00:00Z",
              "status": "BAD_REQUEST",
              "message": "Validation error",
              "details": "origin.lat: Invalid latitude; mode: Must be 'driving', 'walking', or 'bicycling'"
            }""";

    private static final String EXAMPLE_ROUTE_NOT_FOUND = """
            {
              "timestamp": "2025-08-10T14:01:00Z",
              "status": "NOT_FOUND",
              "message": "Route not found",
              "details": "No route exists between the provided origin and destination"
            }""";

    /**
     * Creates an instance of RouteRestController with the RoutePlanner service.
     *
     * @param routePlanner Service responsible for planning routes.
     */
    public RouteRestController(RoutePlanner routePlanner) {
        this.routePlanner = routePlanner;
    }

    /**
     * Plans a route based on the provided origin, destination, and transport mode.
     * The request body is validated using Jakarta Bean Validation.
     * An audit entry is created for each successful planning.
     *
     * @param request RouteRequest object with origin, destination, and desired mode.
     * @return ResponseEntity with the details of the calculated route.
     */
    @PostMapping(ApiConstantsRequestMapping.ROUTERESTCONTROLLER_PM_PLAN_ROUTE_PATH)
    @AuditOperation("Route Planned")
    @Operation(summary = "Plan a route", description = "Calculates a route between two points using a transport mode")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Route calculated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RouteResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Example Route",
                                            summary = "Successful response",
                                            value = EXAMPLE_ROUTE_RESPONSE)
                            }
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request (incorrect or incomplete parameters)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Validation Error",
                                            summary = "Incorrect request",
                                            value = EXAMPLE_VALIDATION_ERROR)
                            }
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No route was found between the given points",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Route Not Found",
                                            summary = "Non-existent route",
                                            value = EXAMPLE_ROUTE_NOT_FOUND)
                            }
                    )
            )
    })
    public ResponseEntity<RouteResponse> planRoute(@Valid @RequestBody RouteRequest request) {
        RouteResponse response = routePlanner.planRoute(request);
        return ResponseEntity.ok(response);
    }
}
