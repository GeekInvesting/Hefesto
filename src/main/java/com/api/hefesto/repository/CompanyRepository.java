package com.api.hefesto.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.hefesto.model.CompanyModel;

public interface CompanyRepository extends JpaRepository<CompanyModel, UUID> {
    
}
