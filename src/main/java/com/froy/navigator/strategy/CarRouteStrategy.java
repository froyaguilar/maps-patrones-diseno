package com.froy.navigator.strategy;

import com.froy.navigator.dto.RouteRequest;
import com.froy.navigator.dto.RouteResponse;
import com.froy.navigator.util.DistanceCalculator;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Concrete strategy for planning routes by car.
 * Prioritizes speed and uses highways where appropriate.
 */
@Component("CAR")
public class CarRouteStrategy implements RouteStrategy {

    @Override
    public RouteResponse compute(RouteRequest request) {
        double distance = DistanceCalculator.calculateDistance(request.origin(), request.destination());
        // Simulate faster travel for cars on highways
        int duration = (int) (distance / 80 * 60); // 80 km/h average speed
        List<String> steps = List.of(
                "Start at " + request.origin(),
                "Drive on major highways",
                "Arrive at " + request.destination()
        );
        return new RouteResponse(round(distance), duration, steps, mode());
    }

    @Override
    public String mode() {
        return "CAR";
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
