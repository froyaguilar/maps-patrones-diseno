package com.froy.navigator.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom validation annotation to ensure a value is a valid geographical coordinate.
 * Works for both latitude and longitude.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GeoCoordinatesValidator.class)
public @interface GeoCoordinates {
    String message() default "Invalid geographical coordinates";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
