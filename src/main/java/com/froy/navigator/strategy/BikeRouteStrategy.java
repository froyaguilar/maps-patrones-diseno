package com.froy.navigator.strategy;

import com.froy.navigator.dto.RouteRequest;
import com.froy.navigator.dto.RouteResponse;
import com.froy.navigator.util.DistanceCalculator;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Concrete strategy for planning routes by bike.
 * Avoids highways and prefers shorter, more scenic routes.
 */
@Component("BIKE")
public class BikeRouteStrategy implements RouteStrategy {

    @Override
    public RouteResponse compute(RouteRequest request) {
        double distance = DistanceCalculator.calculateDistance(request.origin(), request.destination());
        // Simulate slower travel for bikes, avoiding highways
        int duration = (int) (distance / 15 * 60); // 15 km/h average speed
        List<String> steps = List.of(
                "Start at " + request.origin(),
                "Follow bike paths and secondary roads",
                "Avoid highways",
                "Arrive at " + request.destination()
        );
        return new RouteResponse(round(distance), duration, steps, mode());
    }

    @Override
    public String mode() {
        return "BIKE";
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
