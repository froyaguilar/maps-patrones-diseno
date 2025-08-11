package com.froy.navigator.strategy;

import com.froy.navigator.dto.RouteRequest;
import com.froy.navigator.dto.RouteResponse;
import com.froy.navigator.util.DistanceCalculator;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Estrategia concreta para planificar rutas en automóvil.
 * Prioriza la velocidad y utiliza autopistas cuando es apropiado.
 */
@Component("CAR")
public class CarRouteStrategy implements RouteStrategy {

    @Override
    public RouteResponse compute(RouteRequest request) {
        double distance = DistanceCalculator.calculateDistance(request.origin(), request.destination());
        // Simula un viaje más rápido para autos en autopistas
        int duration = (int) (distance / 80 * 60); // velocidad promedio 80 km/h
        List<String> steps = List.of(
                "Inicio en " + request.origin(),
                "Conduce por autopistas principales",
                "Llegada a " + request.destination()
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
