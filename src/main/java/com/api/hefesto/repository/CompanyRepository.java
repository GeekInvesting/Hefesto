package com.api.hefesto.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.hefesto.model.CompanyModel;

public interface CompanyRepository extends JpaRepository<CompanyModel, UUID> {
    
    public Optional<CompanyModel> findByCompanyNameIgnoreCase(String companyName);

    //@Query("SELECT c.countryName FROM CountryModel c WHERE c.countryEnabled = true AND c.countryDeleted = false")

    @Query("SELECT c.companyName FROM CompanyModel c WHERE c.companyEnabled = true AND c.companyDeleted = false")
    public List<String> findAllCompanyName();

    public Boolean existsByCompanyNameIgnoreCase(String companyName);
}
