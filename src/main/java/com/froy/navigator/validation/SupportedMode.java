package com.froy.navigator.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación de validación personalizada para asegurar que un valor represente un modo de transporte soportado.
 * <p>
 * <b>¿Cómo funciona?</b>
 * <ol>
 *   <li><b>{@code @Target({ElementType.FIELD, ElementType.PARAMETER})}</b>: Define que esta anotación
 *       solo se puede colocar sobre campos de una clase o parámetros de un método.</li>
 *   <li><b>{@code @Retention(RetentionPolicy.RUNTIME)}</b>: Asegura que la anotación esté
 *       disponible durante la ejecución del programa, para que el framework de validación
 *       pueda leerla y procesarla.</li>
 *   <li><b>{@code @Constraint(validatedBy = SupportedModeValidator.class)}</b>: Es el enlace clave.
 *       Indica que la lógica de validación para {@code @SupportedMode} se encuentra en la clase
 *       {@link SupportedModeValidator}.</li>
 * </ol>
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SupportedModeValidator.class)
public @interface SupportedMode {
    /**
     * El mensaje de error que se mostrará si la validación falla.
     * El valor `default` se usa si no se especifica un mensaje personalizado
     * al usar la anotación (ej. @SupportedMode(message = "Tu mensaje aquí")).
     *
     * @return el mensaje de error.
     */
    String message() default "Modo de transporte no soportado. Los modos permitidos son CAR, BIKE, MOTORCYCLE.";

    /**
     * Permite especificar grupos de validación.
     * Esto es útil para activar validaciones solo en ciertos escenarios (ej. solo al crear, no al actualizar).
     * Por defecto, pertenece al grupo `Default`.
     *
     * @return los grupos de validación a los que pertenece esta restricción.
     */
    Class<?>[] groups() default {};

    /**
     * Permite asociar metadatos adicionales con una restricción de validación.
     * Se usa en casos avanzados, por ejemplo, para asociar un nivel de severidad (ej. WARNING, FATAL) al error.
     *
     * @return la clase de payload.
     */
    Class<? extends Payload>[] payload() default {};
}
