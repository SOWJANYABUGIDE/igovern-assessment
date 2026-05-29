package com.igovern.program.controller;

import com.igovern.program.dto.ResearchProgramRequestDTO;
import com.igovern.program.dto.ResearchProgramResponseDTO;
import com.igovern.program.entity.ProgramStatus;
import com.igovern.program.service.ResearchProgramService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/programs")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ResearchProgramController {

    private final ResearchProgramService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResearchProgramResponseDTO createProgram(
            @Valid @RequestBody ResearchProgramRequestDTO requestDTO) {

        log.info(
                "Create research program request received with programCode: {}",
                requestDTO.getProgramCode()
        );

        ResearchProgramResponseDTO response =
                service.createProgram(requestDTO);

        log.info(
                "Research program created successfully with id: {}",
                response.getId()
        );

        return response;
    }

    @GetMapping("/{id}")
    public ResearchProgramResponseDTO getProgramById(
            @PathVariable UUID id) {

        log.info(
                "Fetch research program request received for id: {}",
                id
        );

        return service.getProgramById(id);
    }

    @GetMapping
    public Page<ResearchProgramResponseDTO> getAllPrograms(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "title")
            String sortBy) {

        log.info(
                "Fetch all research programs request received. page={}, size={}, sortBy={}",
                page,
                size,
                sortBy
        );

        return service.getAllPrograms(
                page,
                size,
                sortBy
        );
    }

    @PutMapping("/{id}")
    public ResearchProgramResponseDTO updateProgram(
            @PathVariable UUID id,
            @Valid @RequestBody ResearchProgramRequestDTO requestDTO) {

        log.info(
                "Update research program request received for id: {}",
                id
        );

        ResearchProgramResponseDTO response =
                service.updateProgram(id, requestDTO);

        log.info(
                "Research program updated successfully with id: {}",
                response.getId()
        );

        return response;
    }

    @GetMapping("/search")
    public ResearchProgramResponseDTO getByProgramCode(
            @RequestParam String programCode) {

        log.info(
                "Search research program by programCode: {}",
                programCode
        );

        return service.findByProgramCode(programCode);
    }

    @GetMapping("/status")
    public List<ResearchProgramResponseDTO> getByStatus(
            @RequestParam ProgramStatus status) {

        log.info(
                "Search research programs by status: {}",
                status
        );

        return service.findByStatus(status);
    }

    @GetMapping("/title")
    public List<ResearchProgramResponseDTO> getByTitle(
            @RequestParam String title) {

        log.info(
                "Search research programs by title: {}",
                title
        );

        return service.findByTitle(title);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProgram(
            @PathVariable UUID id) {

        log.info(
                "Delete research program request received for id: {}",
                id
        );

        service.deleteProgram(id);

        log.info(
                "Research program deleted successfully with id: {}",
                id
        );
    }
}