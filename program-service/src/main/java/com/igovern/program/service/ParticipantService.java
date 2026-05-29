package com.igovern.program.service;

import com.igovern.program.dto.ParticipantRequestDTO;
import com.igovern.program.dto.ParticipantResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ParticipantService {

    ParticipantResponseDTO addParticipant(
            UUID programId,
            ParticipantRequestDTO requestDTO);

    ParticipantResponseDTO getParticipantById(
            UUID id);
    
    List<ParticipantResponseDTO> getAllParticipants();
    List<ParticipantResponseDTO>
    getParticipantsByProgram(
            UUID programId);

    ParticipantResponseDTO updateParticipant(
            UUID id,
            ParticipantRequestDTO requestDTO);

    void deleteParticipant(UUID id);
    
    ParticipantResponseDTO uploadConsentFile(
            UUID participantId,
            org.springframework.web.multipart.MultipartFile file);

    org.springframework.core.io.Resource downloadConsentFile(
            UUID participantId);
}