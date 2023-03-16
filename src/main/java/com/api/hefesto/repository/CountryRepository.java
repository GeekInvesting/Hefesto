package com.api.hefesto.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.hefesto.model.CountryModel;

public interface CountryRepository extends JpaRepository<CountryModel, UUID> {
    
}
