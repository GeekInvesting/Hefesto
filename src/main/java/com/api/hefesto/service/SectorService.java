package com.api.hefesto.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.hefesto.model.SectorModel;
import com.api.hefesto.repository.SectorRepository;

@Service
public class SectorService {

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
}
