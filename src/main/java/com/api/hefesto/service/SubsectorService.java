package com.api.hefesto.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.api.hefesto.dto.SubsectorDto;
import com.api.hefesto.model.SectorModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.hefesto.model.SubsectorModel;
import com.api.hefesto.repository.SubsectorRepository;

@Service
public class SubsectorService {
    private static final Logger LOG = LogManager.getLogger(SubsectorService.class);

    @Autowired
    private SubsectorRepository subsectorRepository;

    public SubsectorModel saveSubsector(SubsectorModel subsectorModel) {
        return subsectorRepository.save(subsectorModel);
    }

    public boolean existsByName(String subsectorName) {
        return subsectorRepository.existsBySubsectorNameIgnoreCase(subsectorName);
    }

    public boolean existsByNameAndIdNot(String subsectorName, UUID id) {
        return subsectorRepository.existsBySubsectorNameIgnoreCaseAndIdNot(subsectorName, id);
    }

    public Optional<SubsectorModel> getSubsectorByName(String subsectorName) {
        return subsectorRepository.findBySubsectorNameIgnoreCase(subsectorName);
    }

    public List<SubsectorModel> getAll(){
        return subsectorRepository.findAll();
    }

    public Optional<SubsectorModel> getSubsectorById(UUID id){
        return subsectorRepository.findById(id);
    }

    public List<String> listAllSubsectorName(){
        return subsectorRepository.listAllSubsectorName();
    }

    public void saveSubsectorByConsumer(SubsectorDto subsectorDto, SectorModel sectorModel) {
        LOG.info("Subsector Save of Listener: " + subsectorDto.getSubsectorName());
        SubsectorModel subsector = subsectorRepository.findBySubsectorNameIgnoreCase(subsectorDto.getSubsectorName()).orElse(null);
        if(subsector == null){
            subsectorRepository.save(new SubsectorModel(null, subsectorDto.getSubsectorName().toUpperCase(), true, false, sectorModel));
        } else {
            subsector.setSubsectorEnabled(true);
            subsector.setSubsectorDeleted(false);
            subsector.setSectorModel(sectorModel);
            subsectorRepository.save(subsector);
        }
    }
}
