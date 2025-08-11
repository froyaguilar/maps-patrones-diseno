package com.froy.navigator.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validador para la anotación @GeoCoordinates.
 * Verifica si un valor double está dentro del rango válido para latitud o longitud.
 */
public class GeoCoordinatesValidator implements ConstraintValidator<GeoCoordinates, Double> {

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        // Validación básica para latitud (-90 a 90) y longitud (-180 a 180)
        return value >= -180 && value <= 180;
    }
}
