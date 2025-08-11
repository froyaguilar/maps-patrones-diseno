package com.froy.navigator.service.auditing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación personalizada para marcar métodos que deben ser auditados.
 * Cuando se ejecuta un método anotado con @AuditOperation, AuditAspect lo intercepta
 * y crea una entrada de auditoría en la base de datos.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditOperation {
    /**
     * Descripción de la operación auditada.
     * @return Cadena que representa la acción realizada.
     */
    String value();
}
