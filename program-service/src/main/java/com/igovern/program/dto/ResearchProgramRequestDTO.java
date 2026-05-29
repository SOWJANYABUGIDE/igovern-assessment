package com.igovern.program.dto;

import com.igovern.program.entity.ProgramStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ResearchProgramRequestDTO {

    @NotBlank(message = "Program code is required")
    @Size(max = 50)
    private String programCode;

    @NotBlank(message = "Title is required")
    @Size(max = 150)
    private String title;

    @Size(max = 1000)
    private String description;

    @NotNull(message = "Budget is required")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal budget;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    
    @NotNull(message = "Start date is required")
    private LocalDate endDate;

    @NotNull(message = "Status is required")
    private ProgramStatus status;
}