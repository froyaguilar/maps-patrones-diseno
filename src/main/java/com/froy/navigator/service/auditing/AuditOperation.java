package com.froy.navigator.service.auditing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation to mark methods for auditing.
 * When a method annotated with @AuditOperation is executed, AuditAspect will intercept it
 * and create an AuditEntry in the database.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditOperation {
    /**
     * The description of the audited operation.
     * @return a String representing the action performed.
     */
    String value();
}
