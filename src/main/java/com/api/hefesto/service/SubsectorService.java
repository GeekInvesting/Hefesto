package com.api.hefesto.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.hefesto.model.SubsectorModel;
import com.api.hefesto.repository.SubsectorRepository;

@Service
public class SubsectorService {

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
}
