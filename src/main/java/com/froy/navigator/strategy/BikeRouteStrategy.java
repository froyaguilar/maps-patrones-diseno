package com.froy.navigator.strategy;

import com.froy.navigator.dto.RouteRequest;
import com.froy.navigator.dto.RouteResponse;
import com.froy.navigator.util.DistanceCalculator;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Estrategia concreta para planificar rutas en bicicleta.
 * Evita las autopistas y prefiere rutas más cortas y escénicas.
 */
@Component("BIKE")
public class BikeRouteStrategy implements RouteStrategy {

    @Override
    public RouteResponse compute(RouteRequest request) {
        double distance = DistanceCalculator.calculateDistance(request.origin(), request.destination());
        // Simula un viaje más lento para bicicletas, evitando autopistas
        int duration = (int) (distance / 15 * 60); // velocidad promedio 15 km/h
        List<String> steps = List.of(
                "Inicio en " + request.origin(),
                "Sigue ciclovías y carreteras secundarias",
                "Evita autopistas",
                "Llegada a " + request.destination()
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
