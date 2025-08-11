package com.froy.navigator.service.auditing;

import com.froy.navigator.entity.AuditEntry;
import com.froy.navigator.repository.AuditEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for managing audit operations.
 * Responsible for saving audit entries to the database.
 */
@Service
public class AuditService {

    private final AuditEntryRepository auditEntryRepository;

    /**
     * Constructs an AuditService with the given AuditEntryRepository.
     *
     * @param auditEntryRepository The repository for audit entries.
     */
    @Autowired
    public AuditService(AuditEntryRepository auditEntryRepository) {
        this.auditEntryRepository = auditEntryRepository;
    }

    /**
     * Saves a new audit entry to the database.
     * Uses Propagation.REQUIRES_NEW to ensure the audit entry is saved
     * even if the calling transaction fails.
     *
     * @param action The action performed (e.g., "Route Planned").
     * @param message A detailed message about the audit event.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void audit(String action, String message) {
        AuditEntry auditEntry = new AuditEntry(action, message);
        auditEntryRepository.save(auditEntry);
    }
}
