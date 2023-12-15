package com.api.hefesto.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Optional<CompanyModel> getCompanyById(UUID id){
        return companyRepository.findById(id);
    }

    public Optional<CompanyModel> getCompanyByName(String companyName){
        return companyRepository.findByCompanyNameIgnoreCase(companyName);
    }

    public List<String> listCompanyName(){
        return companyRepository.findAllCompanyName();
    }

    public boolean existsCompanyByName(String companyName){
        return companyRepository.existsByCompanyNameIgnoreCase(companyName);
    }

    public void saveCompanyByConsumer(CompanyModel companySave){
    CompanyModel company = companyRepository.findByCompanyNameIgnoreCase(companySave.getCompanyName()).orElse(null);
        if(company == null){
            companyRepository.save(companySave);
        } else {
            companySave.setId(company.getId());
            companyRepository.save(companySave);
        }
    }
}
