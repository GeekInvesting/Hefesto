package com.api.hefesto.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.hefesto.model.SubsectorModel;

public interface SubsectorRepository extends JpaRepository<SubsectorModel, UUID> {
    
    public boolean existsBySubsectorNameIgnoreCase(String subsectorName);
    public boolean existsBySubsectorNameIgnoreCaseAndIdNot(String subsectorName, UUID id);

    public Optional<SubsectorModel> findBySubsectorNameIgnoreCase(String subsectorName);
}
