package com.froy.navigator.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad que representa una entrada de auditoría para operaciones importantes de la aplicación.
 * Almacena detalles como acción, marca de tiempo y mensajes relevantes.
 */
@Entity
@Table(name = "audit_entries")
public class AuditEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Lob // Para campos de texto potencialmente grandes
    private String message;

    // Constructor por defecto para JPA
    public AuditEntry() {
    }

    public AuditEntry(String action, String message) {
        this.action = action;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AuditEntry{" +
               "id=" + id +
               ", action='" + action + '\'' +
               ", timestamp=" + timestamp +
               ", message='" + message + '\'' +
               '}';
    }
}
