package com.igovern.program.dto;

import com.igovern.program.entity.ProgramStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ResearchProgramResponseDTO {

    private UUID id;
    private String programCode;
    private String title;
    private String description;
    private BigDecimal budget;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProgramStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}