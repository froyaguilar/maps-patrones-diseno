package com.froy.navigator.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator for the @GeoCoordinates annotation.
 * Checks if a double value is within the valid range for latitude or longitude.
 */
public class GeoCoordinatesValidator implements ConstraintValidator<GeoCoordinates, Double> {

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        // Basic validation for latitude (-90 to 90) and longitude (-180 to 180)
        return value >= -180 && value <= 180;
    }
}
