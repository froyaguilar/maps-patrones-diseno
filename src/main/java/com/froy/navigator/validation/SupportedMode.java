package com.froy.navigator.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación de validación personalizada para asegurar que un valor represente un modo de transporte soportado.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SupportedModeValidator.class)
public @interface SupportedMode {
    String message() default "Modo de transporte no soportado. Los modos permitidos son CAR, BIKE, MOTORCYCLE.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
