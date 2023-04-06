package com.api.hefesto.controller;

import java.net.URI;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.hefesto.controller.exception.handler.DataConflictException;
import com.api.hefesto.controller.exception.handler.ResourceNotFoundException;
import com.api.hefesto.model.SectorModel;
import com.api.hefesto.service.SectorService;

import jakarta.validation.Valid;
import jakarta.ws.rs.NotAcceptableException;

@RestController
@CrossOrigin("*")
@RequestMapping("hefesto/sector")
public class SectorController {
    private static final Logger LOG = LogManager.getLogger(SectorController.class);

    @Autowired
    private SectorService sectorService;

    private SectorModel sectorModel;

    @PostMapping
    public ResponseEntity<Object> createdSector(@Valid @RequestBody SectorModel sectorDto) throws Exception {
        LOG.info("Create sector: " + sectorDto.toString());

        if (sectorDto.getSectorName() == null) {
            throw new NotAcceptableException("Sector Name is Required!");
        }
        if (sectorService.existsByName(sectorDto.getSectorName())) {
            throw new DataConflictException("Sector Name already exists! " + sectorDto.getSectorName());
        }
        sectorModel.setSectorName(sectorDto.getSectorName().trim().toUpperCase());
        sectorModel.setId(null);
        sectorModel.setSectorEnabled(true);
        sectorModel.setSectorDeleted(false);

        SectorModel sectorCreated = sectorService.saveSector(sectorModel);

        if (sectorCreated == null) {
            throw new Exception("Create Sector as Failed!");
        }

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(sectorCreated.getId())
                .toUri();

        return ResponseEntity.created(uri).body(sectorCreated);
    }

    @GetMapping("all")
    public ResponseEntity<Object> getAllSector() throws Exception {
        LOG.info("Get all Sector!");

        return ResponseEntity.status(HttpStatus.OK).body(sectorService.getAllSector());
    }

    @GetMapping
    public ResponseEntity<Object> getAllSectorNotDeleted() throws Exception {
        LOG.info("Get all Sector not Deleted!");

        List<SectorModel> listSector = sectorService.getAllSectorNotDeleted();
        if (listSector.isEmpty()) {
            throw new ResourceNotFoundException("Sector not Found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(listSector);
    }

    // TODO: Finalizar endpoints Sector
}
