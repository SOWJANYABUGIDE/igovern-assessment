package com.igovern.program.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(
        name = "research_programs",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_program_code", columnNames = "program_code")
        }
)
public class ResearchProgram extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "program_code", nullable = false, length = 50)
    private String programCode;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal budget;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ProgramStatus status = ProgramStatus.DRAFT;

    @OneToMany(
            mappedBy = "researchProgram",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Participant> participants = new ArrayList<>();
}