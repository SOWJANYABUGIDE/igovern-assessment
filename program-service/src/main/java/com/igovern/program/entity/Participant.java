package com.igovern.program.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(
        name = "participants",
        indexes = {
                @Index(name = "idx_participant_email", columnList = "email")
        }
)
public class Participant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(nullable = false)
    private Integer age;

    @Column(length = 20)
    private String gender;

    @Column(name = "enrollment_date", nullable = false)
    private LocalDate enrollmentDate;

    @Column(name = "medical_condition", length = 500)
    private String medicalCondition;

    @Column(name = "consent_file_path", length = 500)
    private String consentFilePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", nullable = false)
    private ResearchProgram researchProgram;
}