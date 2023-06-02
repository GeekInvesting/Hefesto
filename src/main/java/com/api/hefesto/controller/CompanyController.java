package com.api.hefesto.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

        if (StringUtils.isBlank(companyDto.getCompanyName())) {
            throw new NotAcceptableException("Company name is required");
        }

        CompanyModel companyModel = new CompanyModel(
                companyDto.getCompanyName().toUpperCase().trim());

        if (StringUtils.isNotBlank(companyDto.getCompanyCode())) {
            companyModel.setCompanyCode(companyDto.getCompanyCode().toUpperCase().trim());
        }
        if (StringUtils.isNotBlank(companyDto.getCompanyLogo())) {
            companyModel.setCompanyLogo(companyDto.getCompanyLogo().trim());
        }
        if (StringUtils.isNotBlank(companyDto.getCompanyAbout())) {
            companyModel.setCompanyAbout(companyDto.getCompanyAbout().trim().toUpperCase());
        }
        if (StringUtils.isNotBlank(companyDto.getCompanyMainActivity())) {
            companyModel.setCompanyMainActivity(companyDto.getCompanyMainActivity().trim().toUpperCase());
        }
        if (StringUtils.isNotBlank(companyDto.getCompanySiteRi())) {
            companyModel.setCompanySiteRi(companyDto.getCompanySiteRi().trim());
        }

        companyModel.setCompanyEnabled(true);
        companyModel.setCompanyDeleted(false);

        CompanyModel companyCreate = companyService.saveCompany(companyModel);

        if (companyCreate == null) {
            throw new NotAcceptableException("Error to create company");
        }

        // TODO: Implementar serviço preencher dados companhia

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(companyCreate.getId()).toUri();

        return ResponseEntity.created(location).body(companyCreate);
    }

    @GetMapping("all")
    public ResponseEntity<Object> getAllCompany() {
        LOG.info("Get All Company");

        List<CompanyModel> companyList = companyService.getAllCompany();

        if (companyList == null) {
            throw new NotAcceptableException("Error to get all company");
        }

        return ResponseEntity.ok(companyList);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateCompany(@PathVariable UUID id, @RequestBody CompanyModel companyDto) {
        LOG.info("Update Company: " + companyDto.toString());

        Optional<CompanyModel> companyModel = companyService.getCompanyById(id);

        if (!companyModel.isPresent()) {
            throw new NotAcceptableException("Company not found");
        }

        if(StringUtils.isBlank(companyDto.getCompanyName())){
            throw new NotAcceptableException("Company name is required");
        }
        companyModel.get().setCompanyName(
                companyModel.get().getCompanyName().equals(companyDto.getCompanyName())
                        ? companyModel.get().getCompanyName()
                        : companyDto.getCompanyName().toUpperCase().trim());

        if (StringUtils.isNotBlank(companyModel.get().getCompanyCode())){
        companyModel.get().setCompanyCode(
                companyModel.get().getCompanyCode().equals(companyDto.getCompanyCode())
                        ? companyModel.get().getCompanyCode()
                        : companyDto.getCompanyCode().toUpperCase().trim());
        } else {
            companyModel.get().setCompanyCode(companyDto.getCompanyCode().toUpperCase().trim());
        }

        if(StringUtils.isNotBlank(companyModel.get().getCompanyLogo())){
        companyModel.get().setCompanyLogo(
                companyModel.get().getCompanyLogo().equals(companyDto.getCompanyLogo())
                        ? companyModel.get().getCompanyLogo()
                        : companyDto.getCompanyLogo().trim());
        } else {
            companyModel.get().setCompanyLogo(companyDto.getCompanyLogo().trim());
        }

        if(StringUtils.isNotBlank(companyModel.get().getCompanyMainActivity())){
        companyModel.get().setCompanyMainActivity(
                companyModel.get().getCompanyMainActivity().equals(companyDto.getCompanyMainActivity())
                        ? companyModel.get().getCompanyMainActivity()
                        : companyDto.getCompanyMainActivity().trim());
        } else {
            companyModel.get().setCompanyMainActivity(companyDto.getCompanyMainActivity().trim());
        }

        if(StringUtils.isNotBlank(companyModel.get().getCompanySiteRi())){
        companyModel.get().setCompanySiteRi(
                companyModel.get().getCompanySiteRi().equals(companyDto.getCompanySiteRi())
                        ? companyModel.get().getCompanySiteRi()
                        : companyDto.getCompanySiteRi().trim());
        } else {
            companyModel.get().setCompanySiteRi(companyDto.getCompanySiteRi().trim());
        }

        if(StringUtils.isNotBlank(companyModel.get().getCompanyAbout())){
        companyModel.get().setCompanyAbout(
                companyModel.get().getCompanyAbout().equals(companyDto.getCompanyAbout())
                        ? companyModel.get().getCompanyAbout()
                        : companyDto.getCompanyAbout().trim().toUpperCase());
        } else {
            companyModel.get().setCompanyAbout(companyDto.getCompanyAbout().trim().toUpperCase());
        }

        companyModel.get().setCompanyEnabled(true);
        companyModel.get().setCompanyDeleted(false);

        CompanyModel companyUpdate = companyService.saveCompany(companyModel.get());

        if (companyUpdate == null) {
            throw new NotAcceptableException("Error to update company");
        }

        //TODO: implement send msg company

        return ResponseEntity.ok(companyUpdate);
    }
}
