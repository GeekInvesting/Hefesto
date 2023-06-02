package com.api.hefesto.service;

import java.util.List;

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

    public List<CompanyModel> getAllCompany(){
        return companyRepository.findAll();
    }
}
