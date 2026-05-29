package com.igovern.audit.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "audit_logs")
@Getter
@Setter
public class AuditLog extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "entity_name")
    private String entityName;

    @Column(name = "entity_id")
    private UUID entityId;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "deleted_by")
    private String deletedBy;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}