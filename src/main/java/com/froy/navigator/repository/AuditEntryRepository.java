package com.froy.navigator.repository;

import com.froy.navigator.entity.AuditEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link AuditEntry} entities.
 * Provides standard CRUD operations and allows for custom queries if needed.
 */
@Repository
public interface AuditEntryRepository extends JpaRepository<AuditEntry, Long> {
}
