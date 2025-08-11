package com.froy.navigator.repository;

import com.froy.navigator.entity.AuditEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz de repositorio para gestionar entidades {@link AuditEntry}.
 * Proporciona operaciones CRUD estándar y permite consultas personalizadas si es necesario.
 */
@Repository
public interface AuditEntryRepository extends JpaRepository<AuditEntry, Long> {
}
