package com.igovern.program.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    private String eventType;
    private String entityName;
    private UUID entityId;
    private String deletedBy;
    private LocalDateTime deletedAt;
}