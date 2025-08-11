package com.froy.navigator.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing statistics for a route calculation.
 * Stores details such as origin, destination, mode, distance, duration, and the time of calculation.
 */
@Entity
@Table(name = "route_stats")
public class RouteStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double originLat;

    @Column(nullable = false)
    private double originLon;

    @Column(nullable = false)
    private double destinationLat;

    @Column(nullable = false)
    private double destinationLon;

    @Column(nullable = false)
    private String modeUsed;

    @Column(nullable = false)
    private double distanceKm;

    @Column(nullable = false)
    private int durationMinutes;

    @Column(nullable = false)
    private LocalDateTime calculationTime;

    // Default constructor for JPA
    public RouteStats() {
    }

    public RouteStats(double originLat, double originLon, double destinationLat, double destinationLon,
                      String modeUsed, double distanceKm, int durationMinutes) {
        this.originLat = originLat;
        this.originLon = originLon;
        this.destinationLat = destinationLat;
        this.destinationLon = destinationLon;
        this.modeUsed = modeUsed;
        this.distanceKm = distanceKm;
        this.durationMinutes = durationMinutes;
        this.calculationTime = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getOriginLat() {
        return originLat;
    }

    public void setOriginLat(double originLat) {
        this.originLat = originLat;
    }

    public double getOriginLon() {
        return originLon;
    }

    public void setOriginLon(double originLon) {
        this.originLon = originLon;
    }

    public double getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(double destinationLat) {
        this.destinationLat = destinationLat;
    }

    public double getDestinationLon() {
        return destinationLon;
    }

    public void setDestinationLon(double destinationLon) {
        this.destinationLon = destinationLon;
    }

    public String getModeUsed() {
        return modeUsed;
    }

    public void setModeUsed(String modeUsed) {
        this.modeUsed = modeUsed;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public LocalDateTime getCalculationTime() {
        return calculationTime;
    }

    public void setCalculationTime(LocalDateTime calculationTime) {
        this.calculationTime = calculationTime;
    }

    @Override
    public String toString() {
        return "RouteStats{" +
               "id=" + id +
               ", originLat=" + originLat +
               ", originLon=" + originLon +
               ", destinationLat=" + destinationLat +
               ", destinationLon=" + destinationLon +
               ", modeUsed='" + modeUsed + '\'' +
               ", distanceKm=" + distanceKm +
               ", durationMinutes=" + durationMinutes +
               ", calculationTime=" + calculationTime +
               '}';
    }
}
