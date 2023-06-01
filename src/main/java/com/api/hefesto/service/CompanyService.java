package com.api.hefesto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.hefesto.model.CompanyModel;
import com.api.hefesto.repository.CompanyRepository;

@Service
public class CompanyService {
    
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyModel saveCompany(CompanyModel companyModel) {
        return companyRepository.save(companyModel);
    }

    
}
