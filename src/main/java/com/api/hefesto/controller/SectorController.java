package com.api.hefesto.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.hefesto.controller.exception.handler.DataConflictException;
import com.api.hefesto.controller.exception.handler.NotAcceptableException;
import com.api.hefesto.controller.exception.handler.ResourceNotFoundException;
import com.api.hefesto.model.SectorModel;
import com.api.hefesto.service.SectorService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("hefesto/sector")
public class SectorController {
    private static final Logger LOG = LogManager.getLogger(SectorController.class);

    @Autowired
    private SectorService sectorService;

    private SectorModel sectorModel = new SectorModel();

    @PostMapping
    public ResponseEntity<Object> createdSector(@Valid @RequestBody SectorModel sectorDto) throws Exception {
        LOG.info("Create sector: " + sectorDto.toString());

        if (StringUtils.isBlank(sectorDto.getSectorName())) {
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

    @GetMapping("{id}")
    public ResponseEntity<Object> getSectorById(@PathVariable UUID id) throws Exception {
        LOG.info("Get Sector by Id: " + id);

        Optional<SectorModel> sector = sectorService.getSectorById(id);
        if (!sector.isPresent()) {
            throw new ResourceNotFoundException("Sector not Found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(sector);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateSector(
            @PathVariable UUID id,
            @Valid @RequestBody SectorModel sectorDto) throws Exception {
        LOG.info("Update Sector: " + sectorDto.toString());

        Optional<SectorModel> sector = sectorService.getSectorById(id);
        if (!sector.isPresent()) {
            throw new ResourceNotFoundException("Sector not Found!");
        }

        if (StringUtils.isBlank(sectorDto.getSectorName())) {
            throw new NotAcceptableException("Sector Name is Required!");
        }

        sectorModel.setSectorName(sectorDto.getSectorName().trim().toUpperCase());
        sectorModel.setId(id);
        sectorModel.setSectorEnabled(sector.get().isSectorEnabled());
        sectorModel.setSectorDeleted(false);

        SectorModel sectorUpdated = sectorService.saveSector(sectorModel);

        if (sectorUpdated == null) {
            throw new Exception("Update Sector as Failed!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(sectorUpdated);
    }

    @PutMapping("enable/{id}")
    public ResponseEntity<Object> enableSector(@PathVariable UUID id) throws Exception {
        LOG.info("Enable Sector: " + id);

        Optional<SectorModel> sector = sectorService.getSectorById(id);
        if (!sector.isPresent()) {
            throw new ResourceNotFoundException("Sector not Found!");
        }

        sectorModel.setSectorName(sector.get().getSectorName());
        sectorModel.setId(id);
        sectorModel.setSectorEnabled(true);
        sectorModel.setSectorDeleted(sector.get().isSectorDeleted());

        SectorModel sectorUpdated = sectorService.saveSector(sectorModel);

        if (sectorUpdated == null) {
            throw new Exception("Enable Sector as Failed!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(sectorUpdated);
    }

    @PutMapping ("disable/{id}")
    public ResponseEntity<Object> disableSector(@PathVariable UUID id) throws Exception {
        LOG.info("Disable Sector: " + id);

        Optional<SectorModel> sector = sectorService.getSectorById(id);
        if (!sector.isPresent()) {
            throw new ResourceNotFoundException("Sector not Found!");
        }

        sectorModel.setSectorName(sector.get().getSectorName());
        sectorModel.setId(id);
        sectorModel.setSectorEnabled(false);
        sectorModel.setSectorDeleted(sector.get().isSectorDeleted());

        SectorModel sectorUpdated = sectorService.saveSector(sectorModel);

        if (sectorUpdated == null) {
            throw new Exception("Disable Sector as Failed!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(sectorUpdated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteSector(@PathVariable UUID id) throws Exception {
        LOG.info("Delete Sector: " + id);

        Optional<SectorModel> sector = sectorService.getSectorById(id);
        if (!sector.isPresent()) {
            throw new ResourceNotFoundException("Sector not Found!");
        }

        sectorModel.setSectorName(sector.get().getSectorName());
        sectorModel.setId(id);
        sectorModel.setSectorEnabled(sector.get().isSectorEnabled());
        sectorModel.setSectorDeleted(true);

        SectorModel sectorUpdated = sectorService.saveSector(sectorModel);

        if (sectorUpdated == null) {
            throw new Exception("Delete Sector as Failed!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(sectorUpdated);
    }
}
