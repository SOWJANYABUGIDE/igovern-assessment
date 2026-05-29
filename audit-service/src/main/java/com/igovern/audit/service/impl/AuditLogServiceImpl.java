package com.igovern.audit.service.impl;

import com.igovern.audit.entity.AuditLog;
import com.igovern.audit.repository.AuditLogRepository;
import com.igovern.audit.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl
        implements AuditLogService {

    private final AuditLogRepository repository;

    @Override
    public List<AuditLog> getAllLogs() {
        return repository.findAll();
    }
}