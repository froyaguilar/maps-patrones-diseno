package com.froy.navigator.strategy;

import com.froy.navigator.dto.RouteRequest;
import com.froy.navigator.dto.RouteResponse;
import com.froy.navigator.util.DistanceCalculator;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Concrete strategy for planning routes by motorcycle.
 * Combines aspects of car and bike strategies, offering a balance of speed and flexibility.
 */
@Component("MOTORCYCLE")
public class MotorcycleRouteStrategy implements RouteStrategy {

    @Override
    public RouteResponse compute(RouteRequest request) {
        double distance = DistanceCalculator.calculateDistance(request.origin(), request.destination());
        // Simulate moderate speed for motorcycles, with some flexibility
        int duration = (int) (distance / 60 * 60); // 60 km/h average speed
        List<String> steps = List.of(
                "Start at " + request.origin(),
                "Take a flexible route, using main roads and some shortcuts",
                "Arrive at " + request.destination()
        );
        return new RouteResponse(round(distance), duration, steps, mode());
    }

    @Override
    public String mode() {
        return "MOTORCYCLE";
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
