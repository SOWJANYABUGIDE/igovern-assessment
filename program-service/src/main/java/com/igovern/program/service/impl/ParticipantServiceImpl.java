package com.igovern.program.service.impl;

import com.igovern.program.dto.ParticipantRequestDTO;
import com.igovern.program.dto.ParticipantResponseDTO;
import com.igovern.program.entity.Participant;
import com.igovern.program.entity.ResearchProgram;
import com.igovern.program.mapper.ParticipantMapper;
import com.igovern.program.repository.ParticipantRepository;
import com.igovern.program.repository.ResearchProgramRepository;
import com.igovern.program.service.FileStorageService;
import com.igovern.program.service.ParticipantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import com.igovern.program.event.DeleteEvent;
import com.igovern.program.event.DeleteEventPublisher;
import java.time.LocalDateTime;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ParticipantServiceImpl
        implements ParticipantService {

    private final ParticipantRepository participantRepository;
    private final ResearchProgramRepository programRepository;
    private final ParticipantMapper mapper;
    private final FileStorageService fileStorageService;
    private final DeleteEventPublisher deleteEventPublisher;
    @Override
    public ParticipantResponseDTO addParticipant(
            UUID programId,
            ParticipantRequestDTO dto) {
    	

        ResearchProgram program =
                programRepository.findById(programId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Research program not found"));

        Participant participant =
                mapper.toEntity(dto);

        participant.setResearchProgram(program);
        
        participant =
                participantRepository.save(participant);

        return mapper.toResponseDTO(participant);
    }
    @Override
    public List<ParticipantResponseDTO> getAllParticipants() {

        return participantRepository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ParticipantResponseDTO getParticipantById(
            UUID id) {

        Participant participant =
                participantRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Participant not found"));

        return mapper.toResponseDTO(participant);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ParticipantResponseDTO>
    getParticipantsByProgram(
            UUID programId) {

        return participantRepository
                .findByResearchProgramId(programId)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Override
    public ParticipantResponseDTO updateParticipant(
            UUID id,
            ParticipantRequestDTO dto) {

        Participant participant =
                participantRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Participant not found"));

        mapper.updateEntity(dto, participant);

        participant =
                participantRepository.save(participant);

        return mapper.toResponseDTO(participant);
    }

    @Override
    public void deleteParticipant(UUID id) {

        Participant participant =
                participantRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Participant not found"));

        participantRepository.delete(participant);

        DeleteEvent event = new DeleteEvent(
                "PARTICIPANT_DELETED",
                "Participant",
                id,
                "system",
                LocalDateTime.now()
        );

        deleteEventPublisher.publish(event);
    }
    
    @Override
    public ParticipantResponseDTO uploadConsentFile(
            UUID participantId,
            MultipartFile file) {

        Participant participant =
                participantRepository.findById(participantId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Participant not found"));

        try {
            String storedFileName =
                    fileStorageService.storeFile(file);

            participant.setConsentFilePath(storedFileName);

            participant =
                    participantRepository.save(participant);

            return mapper.toResponseDTO(participant);

        } catch (Exception ex) {
            throw new RuntimeException(
                    "File upload failed: " + ex.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Resource downloadConsentFile(UUID participantId) {

        Participant participant =
                participantRepository.findById(participantId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Participant not found"));

        if (participant.getConsentFilePath() == null) {
            throw new RuntimeException(
                    "No consent file uploaded for this participant");
        }

        try {
            return fileStorageService.loadFile(
                    participant.getConsentFilePath());

        } catch (Exception ex) {
            throw new RuntimeException(
                    "File download failed: " + ex.getMessage());
        }
    }
}