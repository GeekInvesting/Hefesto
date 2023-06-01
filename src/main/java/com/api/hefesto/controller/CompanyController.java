package com.api.hefesto.controller;

import java.net.URI;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.hefesto.controller.exception.handler.NotAcceptableException;
import com.api.hefesto.model.CompanyModel;
import com.api.hefesto.service.CompanyService;

@CrossOrigin("${cors.origin}")
@RestController
@RequestMapping("hefesto/company")
public class CompanyController {
    private static final Logger LOG = LogManager.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<Object> createCompany(@RequestBody CompanyModel companyDto) {
        LOG.info("Create Company: " + companyDto.toString());

        if(StringUtils.isBlank(companyDto.getCompanyName())){
            throw new NotAcceptableException("Company name is required");
        }

        CompanyModel companyModel = new CompanyModel(
            companyDto.getCompanyName().toUpperCase().trim()
        );

        if(StringUtils.isNotBlank(companyDto.getCompanyCode())){
            companyModel.setCompanyCode(companyDto.getCompanyCode().toUpperCase().trim());
        }
        if(StringUtils.isNotBlank(companyDto.getCompanyLogo())){
            companyModel.setCompanyLogo(companyDto.getCompanyLogo().trim());
        }
        if(StringUtils.isNotBlank(companyDto.getCompanyAbout())){
            companyModel.setCompanyAbout(companyDto.getCompanyAbout().trim().toUpperCase());
        }
        if(StringUtils.isNotBlank(companyDto.getCompanyMainActivity())){
            companyModel.setCompanyMainActivity(companyDto.getCompanyMainActivity().trim().toUpperCase());
        }
        if(StringUtils.isNotBlank(companyDto.getCompanySiteRi())){
            companyModel.setCompanySiteRi(companyDto.getCompanySiteRi().trim());
        }

        companyModel.setCompanyEnabled(true);
        companyModel.setCompanyDeleted(false);

        CompanyModel companyCreate = companyService.saveCompany(companyModel);

        if (companyCreate == null) {
            throw new NotAcceptableException("Error to create company");
        }

        //TODO: Implementar serviço preencher dados companhia

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(companyCreate.getId()).toUri();

        return ResponseEntity.created(location).body(companyCreate);
    }
}
