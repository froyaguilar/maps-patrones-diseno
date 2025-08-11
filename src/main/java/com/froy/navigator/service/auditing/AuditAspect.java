package com.froy.navigator.service.auditing;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Aspect for auditing operations annotated with {@link AuditOperation}.
 * Intercepts method calls, executes the method, and then records an audit entry
 * using the AuditService.
 */
@Aspect
@Component
public class AuditAspect {

    private final AuditService auditService;

    /**
     * Constructs an AuditAspect with the given AuditService.
     *
     * @param auditService The service used to persist audit entries.
     */
    @Autowired
    public AuditAspect(AuditService auditService) {
        this.auditService = auditService;
    }

    /**
     * Around advice for methods annotated with @AuditOperation.
     * It intercepts the method call, executes the original method, and then
     * logs an audit entry with the action defined in the annotation and the method arguments.
     *
     * @param joinPoint The join point representing the intercepted method.
     * @return The result of the original method execution.
     * @throws Throwable if the original method throws an exception.
     */
    @Around("@annotation(com.froy.navigator.service.auditing.AuditOperation)")
    public Object auditOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AuditOperation auditOperation = signature.getMethod().getAnnotation(AuditOperation.class);
        String action = auditOperation.value();

        Object[] args = joinPoint.getArgs();
        String message = String.format("Method: %s, Arguments: %s", signature.getName(), java.util.Arrays.toString(args));

        try {
            Object result = joinPoint.proceed(); // Execute the original method
            auditService.audit(action, message + ", Result: " + result);
            return result;
        } catch (Throwable e) {
            auditService.audit(action, message + ", Error: " + e.getMessage());
            throw e; // Re-throw the exception after auditing
        }
    }
}
