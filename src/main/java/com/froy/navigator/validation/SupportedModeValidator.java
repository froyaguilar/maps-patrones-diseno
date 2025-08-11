package com.froy.navigator.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Validator for the @SupportedMode annotation.
 * Checks if the given string mode is one of the predefined supported modes (CAR, BIKE, MOTORCYCLE).
 */
public class SupportedModeValidator implements ConstraintValidator<SupportedMode, String> {

    private static final Set<String> SUPPORTED_MODES = Stream.of("CAR", "BIKE", "MOTORCYCLE")
            .collect(Collectors.toUnmodifiableSet());

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // Or true, depending on whether null is considered valid for your use case
        }
        return SUPPORTED_MODES.contains(value.toUpperCase());
    }
}
