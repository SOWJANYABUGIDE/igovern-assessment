package com.igovern.program.repository;

import com.igovern.program.entity.ProgramStatus;
import com.igovern.program.entity.ResearchProgram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResearchProgramRepository
        extends JpaRepository<ResearchProgram, UUID> {

    boolean existsByProgramCode(String programCode);

    Optional<ResearchProgram> findByProgramCode(String programCode);

    List<ResearchProgram> findByStatus(ProgramStatus status);

    List<ResearchProgram> findByTitleContainingIgnoreCase(String title);
}