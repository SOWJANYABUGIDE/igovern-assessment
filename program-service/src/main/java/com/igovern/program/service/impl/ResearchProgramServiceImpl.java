package com.igovern.program.service.impl;

import com.igovern.program.dto.ResearchProgramRequestDTO;
import com.igovern.program.dto.ResearchProgramResponseDTO;
import com.igovern.program.entity.ProgramStatus;
import com.igovern.program.entity.ResearchProgram;
import com.igovern.program.mapper.ResearchProgramMapper;
import com.igovern.program.repository.ResearchProgramRepository;
import com.igovern.program.service.ResearchProgramService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import com.igovern.program.event.DeleteEvent;
import com.igovern.program.event.DeleteEventPublisher;
import java.time.LocalDateTime;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ResearchProgramServiceImpl
        implements ResearchProgramService {

    private final ResearchProgramRepository repository;
    private final ResearchProgramMapper mapper;
    private final DeleteEventPublisher deleteEventPublisher;

    @Override
    public ResearchProgramResponseDTO createProgram(
            ResearchProgramRequestDTO dto) {

        if (repository.existsByProgramCode(
                dto.getProgramCode())) {

            throw new RuntimeException(
                    "Program code already exists");
        }

        ResearchProgram entity =
                mapper.toEntity(dto);

        entity = repository.save(entity);

        return mapper.toResponseDTO(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public ResearchProgramResponseDTO getProgramById(
            UUID id) {

        ResearchProgram entity =
                repository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Program not found"));

        return mapper.toResponseDTO(entity);
    }

/*    @Override
    @Transactional(readOnly = true)
    public List<ResearchProgramResponseDTO>
    getAllPrograms() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }
*/
    @Override
    @Transactional(readOnly = true)
    public Page<ResearchProgramResponseDTO>
    getAllPrograms(
            int page,
            int size,
            String sortBy) {

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by(sortBy).ascending()
                );

        return repository.findAll(pageable)
                .map(mapper::toResponseDTO);
    }
    
    @Override
    public ResearchProgramResponseDTO updateProgram(
            UUID id,
            ResearchProgramRequestDTO dto) {

        ResearchProgram entity =
                repository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Program not found"));

        mapper.updateEntity(dto, entity);

        entity = repository.save(entity);

        return mapper.toResponseDTO(entity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public ResearchProgramResponseDTO
    findByProgramCode(String programCode) {

        ResearchProgram program =
                repository.findByProgramCode(programCode)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Program not found"));

        return mapper.toResponseDTO(program);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResearchProgramResponseDTO>
    findByStatus(ProgramStatus status) {

        return repository.findByStatus(status)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResearchProgramResponseDTO>
    findByTitle(String title) {

        return repository
                .findByTitleContainingIgnoreCase(title)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Override
    public void deleteProgram(UUID id) {

        ResearchProgram entity =
                repository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Program not found"));

        repository.delete(entity);

        DeleteEvent event = new DeleteEvent(
                "PROGRAM_DELETED",
                "ResearchProgram",
                id,
                "system",
                LocalDateTime.now()
        );

        deleteEventPublisher.publish(event);
    }
}