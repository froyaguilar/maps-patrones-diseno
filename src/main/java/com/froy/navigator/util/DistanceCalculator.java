package com.froy.navigator.util;

import com.froy.navigator.dto.GeoPoint;

/**
 * Clase de utilidades para calcular distancias entre puntos geográficos.
 * Utiliza la fórmula de Haversine para obtener la distancia de gran círculo
 * entre dos puntos en una esfera dadas sus longitudes y latitudes.
 */
public class DistanceCalculator {

    /**
     * Radio medio de la Tierra en kilómetros. Se expone para reutilizar en pruebas
     * y otros cálculos que necesiten un valor estándar del radio terrestre.
     */
    public static final double EARTH_RADIUS_KM = 6371.0; // Radio de la Tierra en kilómetros

    /**
     * Calcula la distancia entre dos puntos geográficos usando la fórmula de Haversine.
     *
     * @param point1 Primer punto geográfico (origen).
     * @param point2 Segundo punto geográfico (destino).
     * @return Distancia entre los dos puntos en kilómetros.
     */
    public static double calculateDistance(GeoPoint point1, GeoPoint point2) {
        double lat1Rad = Math.toRadians(point1.lat());
        double lon1Rad = Math.toRadians(point1.lon());
        double lat2Rad = Math.toRadians(point2.lat());
        double lon2Rad = Math.toRadians(point2.lon());

        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
}
