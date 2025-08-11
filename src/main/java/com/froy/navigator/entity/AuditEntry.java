package com.froy.navigator.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing an audit entry for important operations within the application.
 * Stores details such as action, timestamp, and any relevant messages.
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

    @Lob // For potentially large text fields
    private String message;

    // Default constructor for JPA
    public AuditEntry() {
    }

    public AuditEntry(String action, String message) {
        this.action = action;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
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
