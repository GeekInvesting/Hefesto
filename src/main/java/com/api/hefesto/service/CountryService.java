package com.api.hefesto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.hefesto.model.CountryModel;
import com.api.hefesto.repository.CountryRepository;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public void saveCountry(CountryModel country) {
        countryRepository.save(country);
    }

    public boolean existsByName(String countryName) {
        return countryRepository.existsByCountryNameIgnoreCase(countryName);
    }

    public Optional<CountryModel> findByName(String countryName) {
        return countryRepository.findByCountryNameIgnoreCase(countryName);
    }

}
