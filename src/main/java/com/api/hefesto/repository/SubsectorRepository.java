package com.api.hefesto.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.hefesto.model.SubsectorModel;

public interface SubsectorRepository extends JpaRepository<SubsectorModel, UUID> {
    
    public boolean existsBySubsectorNameIgnoreCase(String subsectorName);
    public boolean existsBySubsectorNameIgnoreCaseAndIdNot(String subsectorName, UUID id);

    public Optional<SubsectorModel> findBySubsectorNameIgnoreCase(String subsectorName);

    //@Query("SELECT c.countryName FROM CountryModel c WHERE c.countryEnabled = true AND c.countryDeleted = false")

    @Query("SELECT c.subsectorName FROM SubsectorModel c WHERE c.subsectorEnabled = true AND c.subsectorDeleted = false")
    public List<String> listAllSubsectorName();
}
