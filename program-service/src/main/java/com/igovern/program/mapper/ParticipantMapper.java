package com.igovern.program.mapper;

import com.igovern.program.dto.ParticipantRequestDTO;
import com.igovern.program.dto.ParticipantResponseDTO;
import com.igovern.program.entity.Participant;
import org.springframework.stereotype.Component;

@Component
public class ParticipantMapper {

    public Participant toEntity(
            ParticipantRequestDTO dto) {

        Participant participant = new Participant();

        participant.setFullName(dto.getFullName());
        participant.setEmail(dto.getEmail());
        participant.setPhoneNumber(dto.getPhoneNumber());
        participant.setAge(dto.getAge());
        participant.setGender(dto.getGender());
        participant.setEnrollmentDate(dto.getEnrollmentDate());
        participant.setMedicalCondition(
                dto.getMedicalCondition());

        return participant;
    }

    public ParticipantResponseDTO toResponseDTO(
            Participant entity) {

        ParticipantResponseDTO dto =
                new ParticipantResponseDTO();

        dto.setId(entity.getId());
        dto.setFullName(entity.getFullName());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setAge(entity.getAge());
        dto.setGender(entity.getGender());
        dto.setEnrollmentDate(entity.getEnrollmentDate());
        dto.setMedicalCondition(
                entity.getMedicalCondition());
        dto.setConsentFilePath(
                entity.getConsentFilePath());

        if (entity.getResearchProgram() != null) {
            dto.setProgramId(
                    entity.getResearchProgram().getId());
        }

        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }

    public void updateEntity(
            ParticipantRequestDTO dto,
            Participant entity) {

        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setEnrollmentDate(dto.getEnrollmentDate());
        entity.setMedicalCondition(
                dto.getMedicalCondition());
    }
}