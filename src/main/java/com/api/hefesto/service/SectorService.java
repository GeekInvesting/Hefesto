package com.api.hefesto.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.hefesto.model.SectorModel;
import com.api.hefesto.repository.SectorRepository;

@Service
public class SectorService {
    private static final Logger LOG = LogManager.getLogger(SectorService.class);

    @Autowired
    private SectorRepository sectorRepository;

    public SectorModel saveSector(SectorModel sectorModel) {
        return sectorRepository.save(sectorModel);
    }

    public boolean existsByName(String sectorName) {
        return sectorRepository.existsBySectorNameIgnoreCase(sectorName);
    }

    public Optional<SectorModel> getSectorByName(String sectorName) {
        return sectorRepository.findBySectorNameIgnoreCase(sectorName);
    }

    public List<SectorModel> getAllSector() {
        return sectorRepository.findAll();
    }

    public List<SectorModel> getAllSectorNotDeleted(){
        return sectorRepository.findBySectorDeleted(false);
    }

    public Optional<SectorModel> getSectorById(UUID id){
        return sectorRepository.findById(id);
    }

    public List<String> getAllSectorName(){
        return sectorRepository.findAllSectorName();
    }

    public SectorModel saveSectorByName(String sectorName){
        LOG.info("Sector Save of Listener: " + sectorName);
        SectorModel sector = sectorRepository.findBySectorNameIgnoreCase(sectorName).orElse(null);
        if(sector == null){
            return sectorRepository.save(new SectorModel(null,sectorName.toUpperCase(), true, false));
        } else {
            sector.setSectorEnabled(true);
            sector.setSectorDeleted(false);
            return sectorRepository.save(sector);
        }
    }
}
