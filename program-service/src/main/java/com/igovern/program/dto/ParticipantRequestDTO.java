package com.igovern.program.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ParticipantRequestDTO {

    @NotBlank(message = "Full name is required")
    @Size(max = 150)
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 150)
    private String email;

    @Size(max = 20)
    private String phoneNumber;

    @NotNull(message = "Age is required")
    @Min(value = 1)
    @Max(value = 120)
    private Integer age;

    @Size(max = 20)
    private String gender;

    @NotNull(message = "Enrollment date is required")
    private LocalDate enrollmentDate;

    @Size(max = 500)
    private String medicalCondition;
}