package com.froy.navigator.util;

import com.froy.navigator.dto.GeoPoint;

/**
 * Utility class for calculating distances between geographical points.
 * Uses the Haversine formula to calculate the great-circle distance between two points
 * on a sphere given their longitudes and latitudes.
 */
public class DistanceCalculator {

    private static final int EARTH_RADIUS_KM = 6371; // Earth's radius in kilometers

    /**
     * Calculates the distance between two geographical points using the Haversine formula.
     *
     * @param point1 The first geographical point (origin).
     * @param point2 The second geographical point (destination).
     * @return The distance between the two points in kilometers.
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
