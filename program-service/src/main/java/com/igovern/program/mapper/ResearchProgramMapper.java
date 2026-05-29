package com.igovern.program.mapper;

import com.igovern.program.dto.ResearchProgramRequestDTO;
import com.igovern.program.dto.ResearchProgramResponseDTO;
import com.igovern.program.entity.ResearchProgram;
import org.springframework.stereotype.Component;

@Component
public class ResearchProgramMapper {

    public ResearchProgram toEntity(ResearchProgramRequestDTO dto) {

        ResearchProgram program = new ResearchProgram();

        program.setProgramCode(dto.getProgramCode());
        program.setTitle(dto.getTitle());
        program.setDescription(dto.getDescription());
        program.setBudget(dto.getBudget());
        program.setStartDate(dto.getStartDate());
        program.setEndDate(dto.getEndDate());
        program.setStatus(dto.getStatus());

        return program;
    }

    public ResearchProgramResponseDTO toResponseDTO(
            ResearchProgram entity) {

        ResearchProgramResponseDTO dto =
                new ResearchProgramResponseDTO();

        dto.setId(entity.getId());
        dto.setProgramCode(entity.getProgramCode());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setBudget(entity.getBudget());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setStatus(entity.getStatus());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }

    public void updateEntity(
            ResearchProgramRequestDTO dto,
            ResearchProgram entity) {

        entity.setProgramCode(dto.getProgramCode());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setBudget(dto.getBudget());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setStatus(dto.getStatus());
    }
}