package com.api.hefesto.controller;

import java.net.URI;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.hefesto.config.JwtTokenUtil;
import com.api.hefesto.controller.exception.handler.NotAcceptableException;
import com.api.hefesto.controller.exception.handler.UnauthorizedException;
import com.api.hefesto.dto.SubsectorDto;
import com.api.hefesto.model.SectorModel;
import com.api.hefesto.model.SubsectorModel;
import com.api.hefesto.service.SectorService;
import com.api.hefesto.service.SubsectorService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("hefesto/subsector")
public class SubsectorController {
    private static final Logger LOG = LogManager.getLogger(SubsectorController.class);

    @Autowired
    private SubsectorService subsectorService;

    @Autowired
    private SectorService sectorService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private SubsectorModel subsectorModel = new SubsectorModel();

    @PostMapping
    public ResponseEntity<Object> createSubsector(@Valid @RequestBody SubsectorDto subsectorDto, @RequestHeader("authorization") String token){
        LOG.info("Create subsector: " + subsectorDto.toString());
        LOG.info(token);

        /* 
        if(!jwtTokenUtil.isTokenValid(token)){
            throw new UnauthorizedException();
        }
        */

        subsectorModel.setId(null);

        if (StringUtils.isBlank(subsectorDto.getSubsectorName())) {
            throw new NotAcceptableException("Subsector Name is Required!");
        }
        if (subsectorService.existsByName(subsectorDto.getSubsectorName())) {
            throw new NotAcceptableException("Subsector Name already exists! " + subsectorDto.getSubsectorName());
        }
        subsectorModel.setSubsectorName(subsectorDto.getSubsectorName().trim().toUpperCase());

        if (StringUtils.isBlank(subsectorDto.getSectorName())){
            throw new NotAcceptableException("Sector Name is Required!");
        }
        Optional<SectorModel> sectorSearch = sectorService.getSectorByName(subsectorDto.getSectorName());
        if (sectorSearch.isEmpty()){
            throw new NotAcceptableException("Sector Name not found! " + subsectorDto.getSectorName());
        }
        subsectorModel.setSectorModel(sectorSearch.get());

        subsectorModel.setSubsectorEnabled(true);
        subsectorModel.setSubsectorDeleted(false);

        SubsectorModel subsectorCreated = subsectorService.saveSubsector(subsectorModel);

        if (subsectorCreated == null) {
            throw new NotAcceptableException("Subsector not created!");
        }

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(subsectorCreated.getId())
                .toUri();

        return ResponseEntity.created(uri).body(subsectorCreated);
    }

    @GetMapping("all")
    public ResponseEntity<Object> getAllSubsectors(@RequestHeader("authorization") String token){
        LOG.info("Get all subsectors");
        LOG.info(token);

        /* 
        if(!jwtTokenUtil.isTokenValid(token)){
            throw new UnauthorizedException();
        }
        */

        return ResponseEntity.ok(subsectorService.getAll());
    }
    

    //TODO: Implementar demais endpoints Subsector
}
