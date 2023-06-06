package com.api.hefesto.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.hefesto.model.SectorModel;

public interface SectorRepository extends JpaRepository<SectorModel, UUID> {
    
    public boolean existsBySectorNameIgnoreCase(String sectorName);

    public Optional<SectorModel> findBySectorNameIgnoreCase(String sectorName);

    public List<SectorModel> findBySectorDeleted(boolean sectorDeleted);

    //@Query("SELECT c.countryName FROM CountryModel c WHERE c.countryEnabled = true AND c.countryDeleted = false")

    @Query("SELECT s.sectorName FROM SectorModel s WHERE s.sectorEnabled = true AND s.sectorDeleted = false")
    public List<String> findAllSectorName();
}
