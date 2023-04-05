package com.api.hefesto.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.hefesto.model.CountryModel;

public interface CountryRepository extends JpaRepository<CountryModel, UUID> {
    
    boolean existsByCountryNameIgnoreCase(String countryName);

    Optional<CountryModel> findByCountryNameIgnoreCase(String countryName);
}
