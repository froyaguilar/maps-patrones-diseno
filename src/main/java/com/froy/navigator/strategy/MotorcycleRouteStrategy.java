package com.froy.navigator.strategy;

import com.froy.navigator.dto.RouteRequest;
import com.froy.navigator.dto.RouteResponse;
import com.froy.navigator.model.TransportMode;
import com.froy.navigator.util.DistanceCalculator;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Estrategia concreta para planificar rutas en motocicleta.
 * Combina aspectos de las estrategias de auto y bicicleta, ofreciendo un balance
 * entre velocidad y flexibilidad.
 */
@Component
public class MotorcycleRouteStrategy implements RouteStrategy {

    @Override
    public RouteResponse compute(RouteRequest request) {
        double distance = DistanceCalculator.calculateDistance(request.origin(), request.destination());
        // Simula una velocidad moderada para motocicletas, con cierta flexibilidad
        int duration = (int) (distance / 60 * 60); // velocidad promedio 60 km/h
        List<String> steps = List.of(
                "Inicio en " + request.origin(),
                "Toma una ruta flexible, usando carreteras principales y algunos atajos",
                "Llegada a " + request.destination()
        );
        return new RouteResponse(round(distance), duration, steps, getMode().name());
    }

    @Override
    public TransportMode getMode() {
        return TransportMode.MOTORCYCLE;
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
