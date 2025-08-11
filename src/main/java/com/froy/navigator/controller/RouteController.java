package com.froy.navigator.controller;

import com.froy.navigator.dto.RouteRequest;
import com.froy.navigator.dto.RouteResponse;
import com.froy.navigator.service.RoutePlanner;
import com.froy.navigator.service.auditing.AuditOperation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for handling route planning requests.
 * Exposes an endpoint to plan routes using different transport modes.
 */
@RestController
@RequestMapping("/api/v1/routes")
public class RouteController {

    private final RoutePlanner routePlanner;

    /**
     * Constructs a RouteController with the given RoutePlanner.
     *
     * @param routePlanner The service responsible for planning routes.
     */
    public RouteController(RoutePlanner routePlanner) {
        this.routePlanner = routePlanner;
    }

    /**
     * Plans a route based on the provided origin, destination, and transport mode.
     * The request body is validated using Jakarta Bean Validation.
     * An audit entry is created for each successful route planning operation.
     *
     * @param request The RouteRequest containing the origin, destination, and desired mode.
     * @return A ResponseEntity containing the RouteResponse with the calculated route details.
     */
    @PostMapping("/plan")
    @AuditOperation("Route Planned")
    public ResponseEntity<RouteResponse> planRoute(@Valid @RequestBody RouteRequest request) {
        RouteResponse response = routePlanner.planRoute(request);
        return ResponseEntity.ok(response);
    }
}
