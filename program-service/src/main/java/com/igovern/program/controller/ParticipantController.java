package com.igovern.program.controller;

import com.igovern.program.dto.ParticipantRequestDTO;
import com.igovern.program.dto.ParticipantResponseDTO;
import com.igovern.program.service.ParticipantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(
        origins = "http://localhost:4200",
        exposedHeaders = "Content-Disposition"
)
public class ParticipantController {

    private final ParticipantService service;

    @PostMapping("/programs/{programId}/participants")
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipantResponseDTO addParticipant(
            @PathVariable UUID programId,
            @Valid @RequestBody ParticipantRequestDTO requestDTO) {

        log.info(
                "Add participant request received for programId: {}, email: {}",
                programId,
                requestDTO.getEmail()
        );

        ParticipantResponseDTO response =
                service.addParticipant(programId, requestDTO);

        log.info(
                "Participant added successfully with id: {}",
                response.getId()
        );

        return response;
    }
    @GetMapping("/participants")
    public List<ParticipantResponseDTO> getAllParticipants() {
        return service.getAllParticipants();
    }
    @GetMapping("/participants/{id}")
    public ParticipantResponseDTO getParticipantById(
            @PathVariable UUID id) {

        log.info(
                "Fetch participant request received for id: {}",
                id
        );

        return service.getParticipantById(id);
    }

    @GetMapping("/programs/{programId}/participants")
    public List<ParticipantResponseDTO> getParticipantsByProgram(
            @PathVariable UUID programId) {

        log.info(
                "Fetch participants request received for programId: {}",
                programId
        );

        return service.getParticipantsByProgram(programId);
    }

    @PutMapping("/participants/{id}")
    public ParticipantResponseDTO updateParticipant(
            @PathVariable UUID id,
            @Valid @RequestBody ParticipantRequestDTO requestDTO) {

        log.info(
                "Update participant request received for id: {}",
                id
        );

        ParticipantResponseDTO response =
                service.updateParticipant(id, requestDTO);

        log.info(
                "Participant updated successfully with id: {}",
                response.getId()
        );

        return response;
    }

    @DeleteMapping("/participants/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteParticipant(
            @PathVariable UUID id) {

        log.info(
                "Delete participant request received for id: {}",
                id
        );

        service.deleteParticipant(id);

        log.info(
                "Participant deleted successfully with id: {}",
                id
        );
    }

    @PostMapping("/participants/{id}/upload-consent")
    public ParticipantResponseDTO uploadConsentFile(
            @PathVariable UUID id,
            @RequestParam("file") MultipartFile file) {

        log.info(
                "Consent file upload request received for participantId: {}, fileName: {}",
                id,
                file.getOriginalFilename()
        );

        ParticipantResponseDTO response =
                service.uploadConsentFile(id, file);

        log.info(
                "Consent file uploaded successfully for participantId: {}",
                id
        );

        return response;
    }

    @GetMapping("/participants/{id}/download-consent")
    public ResponseEntity<Resource> downloadConsentFile(
            @PathVariable UUID id) {

        log.info(
                "Consent file download request received for participantId: {}",
                id
        );

        Resource resource =
                service.downloadConsentFile(id);

        log.info(
                "Consent file download prepared for participantId: {}, fileName: {}",
                id,
                resource.getFilename()
        );

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" +
                                resource.getFilename() + "\""
                )
                .body(resource);
    }
}