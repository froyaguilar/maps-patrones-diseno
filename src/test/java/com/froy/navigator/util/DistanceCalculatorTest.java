package com.froy.navigator.util;

import com.froy.navigator.dto.GeoPoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the {@link DistanceCalculator} utility class.
 */
class DistanceCalculatorTest {

    private static final double DELTA = 0.01; // Tolerance for double comparisons

    @Test
    @DisplayName("Should calculate distance between two points correctly")
    void shouldCalculateDistanceBetweenTwoPointsCorrectly() {
        // Guadalajara to Mexico City
        GeoPoint origin = new GeoPoint(20.6736, -103.344);
        GeoPoint destination = new GeoPoint(19.4326, -99.1332);

        // Expected distance (approximate, based on online calculators)
        double expectedDistanceKm = 512.0; 

        double actualDistance = DistanceCalculator.calculateDistance(origin, destination);

        assertEquals(expectedDistanceKm, actualDistance, DELTA,
                "Distance calculation between Guadalajara and Mexico City should be accurate");
    }

    @Test
    @DisplayName("Should return zero distance for identical points")
    void shouldReturnZeroDistanceForIdenticalPoints() {
        GeoPoint point1 = new GeoPoint(10.0, 20.0);
        GeoPoint point2 = new GeoPoint(10.0, 20.0);

        double distance = DistanceCalculator.calculateDistance(point1, point2);

        assertEquals(0.0, distance, DELTA, "Distance for identical points should be zero");
    }

    @Test
    @DisplayName("Should calculate distance for points on the equator")
    void shouldCalculateDistanceForPointsOnEquator() {
        // Points on the equator (0 latitude)
        GeoPoint point1 = new GeoPoint(0.0, 0.0); // Prime Meridian
        GeoPoint point2 = new GeoPoint(0.0, 90.0); // 90 degrees East

        // Expected distance: 1/4 of Earth's circumference at equator (approx)
        // Circumference = 2 * PI * R = 2 * 3.14159 * 6371 = 40030 km
        // 1/4 of that is ~10007.5 km
        double expectedDistance = 10007.5;

        double actualDistance = DistanceCalculator.calculateDistance(point1, point2);

        assertEquals(expectedDistance, actualDistance, DELTA,
                "Distance calculation for points on the equator should be accurate");
    }

    @Test
    @DisplayName("Should calculate distance for points on the same longitude")
    void shouldCalculateDistanceForPointsOnSameLongitude() {
        // Points on the same longitude (e.g., along a meridian)
        GeoPoint point1 = new GeoPoint(0.0, 50.0); // Equator at 50E
        GeoPoint point2 = new GeoPoint(90.0, 50.0); // North Pole

        // Expected distance: 1/4 of a great circle (pole to equator)
        double expectedDistance = Math.PI * DistanceCalculator.EARTH_RADIUS_KM / 2; 
        
        double actualDistance = DistanceCalculator.calculateDistance(point1, point2);

        assertEquals(expectedDistance, actualDistance, DELTA,
                "Distance calculation for points on the same longitude should be accurate");
    }

    @Test
    @DisplayName("Should handle negative coordinates correctly")
    void shouldHandleNegativeCoordinatesCorrectly() {
        GeoPoint origin = new GeoPoint(-34.6037, -58.3816); // Buenos Aires
        GeoPoint destination = new GeoPoint(-33.4489, -70.6693); // Santiago

        // Expected distance (approximate)
        double expectedDistanceKm = 1050.0;

        double actualDistance = DistanceCalculator.calculateDistance(origin, destination);

        assertEquals(expectedDistanceKm, actualDistance, DELTA,
                "Distance calculation with negative coordinates should be accurate");
    }

}
