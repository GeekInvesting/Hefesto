package com.api.hefesto.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.hefesto.model.ExchangeModel;

public interface ExchangeRepository extends JpaRepository<ExchangeModel, UUID> {
    
    public boolean existsByExchangeNameIgnoreCase(String exchangeName);
    public boolean existsByExchangeCodeIgnoreCase(String exchangeCode);

    public Optional<ExchangeModel> findByExchangeNameIgnoreCase(String exchangeName);
    public Optional<ExchangeModel> findByExchangeCodeIgnoreCase(String exchangeCode);

    public List<ExchangeModel> findByExchangeDeleted(boolean exchangeDeleted);

    //@Query("SELECT c.countryName FROM CountryModel c WHERE c.countryEnabled = true AND c.countryDeleted = false")

    @Query("select e.exchangeCode from ExchangeModel e where e.exchangeEnabled = true and e.exchangeDeleted = false")
    public List<String> listAllExchangeCode();
}
