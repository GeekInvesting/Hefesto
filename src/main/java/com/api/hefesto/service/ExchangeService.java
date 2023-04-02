package com.api.hefesto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.hefesto.model.ExchangeModel;
import com.api.hefesto.repository.ExchangeRepository;

@Service
public class ExchangeService {
    
    @Autowired
    private ExchangeRepository exchangeRepository;

    public ExchangeModel saveExchange(ExchangeModel exchangeModel){
        return exchangeRepository.save(exchangeModel);
    }
}
