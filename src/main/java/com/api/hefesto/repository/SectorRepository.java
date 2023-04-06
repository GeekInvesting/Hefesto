package com.api.hefesto.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.hefesto.model.SectorModel;

public interface SectorRepository extends JpaRepository<SectorModel, UUID> {
    
    public boolean existsBySectorNameIgnoreCase(String sectorName);

    public Optional<SectorModel> findBySectorNameIgnoreCase(String sectorName);

    public List<SectorModel> findBySectorDeleted(boolean sectorDeleted);
}
