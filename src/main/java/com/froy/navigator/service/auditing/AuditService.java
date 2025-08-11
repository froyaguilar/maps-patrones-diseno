package com.froy.navigator.service.auditing;

import com.froy.navigator.entity.AuditEntry;
import com.froy.navigator.repository.AuditEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para gestionar las operaciones de auditoría.
 * Responsable de guardar las entradas de auditoría en la base de datos.
 */
@Service
public class AuditService {

    private final AuditEntryRepository auditEntryRepository;

    /**
     * Construye un AuditService con el AuditEntryRepository proporcionado.
     *
     * @param auditEntryRepository Repositorio para las entradas de auditoría.
     */
    @Autowired
    public AuditService(AuditEntryRepository auditEntryRepository) {
        this.auditEntryRepository = auditEntryRepository;
    }

    /**
     * Guarda una nueva entrada de auditoría en la base de datos.
     * Usa Propagation.REQUIRES_NEW para asegurar que la entrada se persista
     * incluso si la transacción llamante falla.
     *
     * @param action Acción realizada (p. ej., "Ruta Planificada").
     * @param message Mensaje detallado sobre el evento de auditoría.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void audit(String action, String message) {
        AuditEntry auditEntry = new AuditEntry(action, message);
        auditEntryRepository.save(auditEntry);
    }
}
