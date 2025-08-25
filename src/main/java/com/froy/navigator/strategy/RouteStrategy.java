package com.froy.navigator.strategy;

import com.froy.navigator.dto.RouteRequest;
import com.froy.navigator.dto.RouteResponse;
import com.froy.navigator.model.TransportMode;

/**
 * Interfaz para las estrategias de planificación de rutas.
 * Implementa el patrón Strategy, permitiendo diferentes algoritmos de cálculo
 * según el modo de transporte.
 */
public interface RouteStrategy {

    /**
     * Calcula una ruta basada en la solicitud dada.
     *
     * @param request Petición de ruta que contiene origen, destino y modo.
     * @return RouteResponse con distancia, duración, pasos y modo calculados.
     */
    RouteResponse compute(RouteRequest request);

    /**
     * Devuelve el modo de transporte manejado por esta estrategia.
     *
     * @return El enum TransportMode que maneja esta estrategia.
     */
    TransportMode getMode();
}
