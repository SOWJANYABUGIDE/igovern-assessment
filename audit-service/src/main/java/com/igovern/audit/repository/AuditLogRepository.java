package com.igovern.audit.repository;

import com.igovern.audit.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuditLogRepository
        extends JpaRepository<AuditLog, UUID> {
}