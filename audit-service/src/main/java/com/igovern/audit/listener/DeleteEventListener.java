package com.igovern.audit.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.igovern.audit.dto.DeleteEventDTO;
import com.igovern.audit.entity.AuditLog;
import com.igovern.audit.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteEventListener {

    private final ObjectMapper objectMapper;
    private final AuditLogRepository repository;

    @JmsListener(destination = "igovern.delete.events")
    public void consumeDeleteEvent(String message)
            throws Exception {

        DeleteEventDTO event =
                objectMapper.readValue(
                        message,
                        DeleteEventDTO.class
                );

        AuditLog auditLog = new AuditLog();
        auditLog.setEventType(event.getEventType());
        auditLog.setEntityName(event.getEntityName());
        auditLog.setEntityId(event.getEntityId());
        auditLog.setDeletedBy(event.getDeletedBy());
        auditLog.setDeletedAt(event.getDeletedAt());

        repository.save(auditLog);
    }
}