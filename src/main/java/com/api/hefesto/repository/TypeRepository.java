package com.api.hefesto.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.hefesto.model.TypeModel;

public interface TypeRepository extends JpaRepository<TypeModel, UUID> {
    
    public Optional<TypeModel> findByTypeCodeIgnoreCase(String typeCode);

        //@Query("SELECT c.countryName FROM CountryModel c WHERE c.countryEnabled = true AND c.countryDeleted = false")

    @Query("SELECT c.typeCode FROM TypeModel c WHERE c.typeEnabled = true AND c.typeDeleted = false")
    public List<String> listAllTypeCode();
}
