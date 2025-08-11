package com.froy.navigator.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom validation annotation to ensure a string value represents a supported transport mode.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SupportedModeValidator.class)
public @interface SupportedMode {
    String message() default "Unsupported transport mode. Allowed modes are CAR, BIKE, MOTORCYCLE.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
