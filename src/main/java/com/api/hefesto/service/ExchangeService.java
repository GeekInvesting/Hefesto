package com.api.hefesto.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public boolean existsByName(String exchangeName){
        return exchangeRepository.existsByExchangeNameIgnoreCase(exchangeName);
    }

    public Optional<ExchangeModel> getExchangeByName(String exchangeName){
        return exchangeRepository.findByExchangeNameIgnoreCase(exchangeName);
    }

    public boolean existsByCode(String exchangeCode){
        return exchangeRepository.existsByExchangeCodeIgnoreCase(exchangeCode);
    }

    public Optional<ExchangeModel> getExchangeByCode(String exchangeCode){
        return exchangeRepository.findByExchangeCodeIgnoreCase(exchangeCode);
    }
    
    public List<ExchangeModel> findAllExchange(){
        return exchangeRepository.findAll();
    }

    public List<ExchangeModel> findAllExchangeNotDeleted(){
        return exchangeRepository.findByExchangeDeleted(false);
    }

    public Optional<ExchangeModel> findById(UUID exchangeId){
        return exchangeRepository.findById(exchangeId);
    }
}
