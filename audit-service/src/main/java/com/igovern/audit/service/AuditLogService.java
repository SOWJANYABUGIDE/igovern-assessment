package com.igovern.audit.service;

import com.igovern.audit.entity.AuditLog;

import java.util.List;

public interface AuditLogService {

    List<AuditLog> getAllLogs();
}