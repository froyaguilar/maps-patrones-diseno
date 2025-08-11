package com.froy.navigator.service.auditing;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Aspecto para auditar operaciones anotadas con {@link AuditOperation}.
 * Intercepta las llamadas a métodos, ejecuta el método y luego registra una entrada
 * de auditoría utilizando AuditService.
 */
@Aspect
@Component
public class AuditAspect {

    private final AuditService auditService;

    /**
     * Construye un AuditAspect con el AuditService proporcionado.
     *
     * @param auditService Servicio utilizado para persistir las entradas de auditoría.
     */
    @Autowired
    public AuditAspect(AuditService auditService) {
        this.auditService = auditService;
    }

    /**
     * Consejo alrededor para métodos anotados con @AuditOperation.
     * Intercepta la llamada, ejecuta el método original y registra una entrada de auditoría
     * con la acción definida en la anotación y los argumentos del método.
     *
     * @param joinPoint Punto de unión que representa el método interceptado.
     * @return Resultado de la ejecución del método original.
     * @throws Throwable si el método original lanza una excepción.
     */
    @Around("@annotation(com.froy.navigator.service.auditing.AuditOperation)")
    public Object auditOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AuditOperation auditOperation = signature.getMethod().getAnnotation(AuditOperation.class);
        String action = auditOperation.value();

        Object[] args = joinPoint.getArgs();
        String message = String.format("Método: %s, Argumentos: %s", signature.getName(), java.util.Arrays.toString(args));

        try {
            Object result = joinPoint.proceed(); // Ejecuta el método original
            auditService.audit(action, message + ", Resultado: " + result);
            return result;
        } catch (Throwable e) {
            auditService.audit(action, message + ", Error: " + e.getMessage());
            throw e; // Re-lanza la excepción después de auditar
        }
    }
}
