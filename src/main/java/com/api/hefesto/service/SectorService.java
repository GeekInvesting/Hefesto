package com.api.hefesto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.hefesto.model.SectorModel;
import com.api.hefesto.repository.SectorRepository;

@Service
public class SectorService {
    
    @Autowired
    private SectorRepository sectorRepository;

    public SectorModel saveSector(SectorModel sectorModel){
        return sectorRepository.save(sectorModel);
    }

    public boolean existsByName(String sectorName){
        return sectorRepository.existsBySectorNameIgnoreCase(sectorName);
    }

    public Optional<SectorModel> getSectorByName(String sectorName){
        return sectorRepository.findBySectorNameIgnoreCase(sectorName);
    }
    
}
