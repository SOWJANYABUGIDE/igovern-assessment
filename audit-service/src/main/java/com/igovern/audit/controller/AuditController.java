package com.igovern.audit.controller;

import com.igovern.audit.entity.AuditLog;
import com.igovern.audit.service.AuditLogService;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/audit")
@CrossOrigin(origins = "http://localhost:4200")
public class AuditController {

    private final AuditLogService auditLogService;

    @GetMapping
    public List<AuditLog> getAllLogs() {
    	log.info("Fetching all audit logs");
        return auditLogService.getAllLogs();
    }
}