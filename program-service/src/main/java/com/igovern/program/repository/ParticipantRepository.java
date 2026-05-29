package com.igovern.program.repository;

import com.igovern.program.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ParticipantRepository extends JpaRepository<Participant, UUID> {

    List<Participant> findByResearchProgramId(UUID programId);
}