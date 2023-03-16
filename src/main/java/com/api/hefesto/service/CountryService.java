package com.api.hefesto.service;

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
}
