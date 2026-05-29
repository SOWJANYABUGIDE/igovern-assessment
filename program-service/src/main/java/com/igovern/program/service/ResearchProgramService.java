package com.igovern.program.service;

import com.igovern.program.dto.ResearchProgramRequestDTO;
import com.igovern.program.dto.ResearchProgramResponseDTO;
import com.igovern.program.entity.ProgramStatus;

import java.util.List;
import java.util.UUID;

public interface ResearchProgramService {

    ResearchProgramResponseDTO createProgram(
            ResearchProgramRequestDTO requestDTO);

    ResearchProgramResponseDTO getProgramById(
            UUID id);

    //List<ResearchProgramResponseDTO> getAllPrograms();
    org.springframework.data.domain.Page<ResearchProgramResponseDTO>
    getAllPrograms(
            int page,
            int size,
            String sortBy);

    ResearchProgramResponseDTO updateProgram(
            UUID id,
            ResearchProgramRequestDTO requestDTO);
    
    List<ResearchProgramResponseDTO>
    findByStatus(ProgramStatus status);

    ResearchProgramResponseDTO
    findByProgramCode(String programCode);

    List<ResearchProgramResponseDTO>
    findByTitle(String title);

    void deleteProgram(UUID id);
}