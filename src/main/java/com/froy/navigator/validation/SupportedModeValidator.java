package com.froy.navigator.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Validador para la anotación @SupportedMode.
 * Verifica si el modo proporcionado es uno de los modos soportados (CAR, BIKE, MOTORCYCLE).
 */
public class SupportedModeValidator implements ConstraintValidator<SupportedMode, String> {

    private static final Set<String> SUPPORTED_MODES = Stream.of("CAR", "BIKE", "MOTORCYCLE")
            .collect(Collectors.toUnmodifiableSet());

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // O true, dependiendo de si se permite null en tu caso de uso
        }
        return SUPPORTED_MODES.contains(value.toUpperCase());
    }
}
