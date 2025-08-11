package com.froy.navigator.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación de validación personalizada para asegurar que un valor sea una coordenada geográfica válida.
 * Funciona tanto para latitud como para longitud.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GeoCoordinatesValidator.class)
public @interface GeoCoordinates {
    String message() default "Coordenadas geográficas inválidas";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
