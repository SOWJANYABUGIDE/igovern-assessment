package com.igovern.program.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ParticipantResponseDTO {

    private UUID id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Integer age;
    private String gender;
    private LocalDate enrollmentDate;
    private String medicalCondition;
    private String consentFilePath;
    private UUID programId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}