package com.igovern.audit.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class DeleteEventDTO {

    private String eventType;
    private String entityName;
    private UUID entityId;
    private String deletedBy;
    private LocalDateTime deletedAt;
}