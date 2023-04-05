package com.api.hefesto.repository;

import java.util.Optional;

import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.hefesto.model.ExchangeModel;

public interface ExchangeRepository extends JpaRepository<ExchangeModel, UUID> {
    
    public boolean existsByExchangeNameIgnoreCase(String exchangeName);
    public boolean existsByExchangeCodeIgnoreCase(String exchangeCode);

    public Optional<ExchangeModel> findByExchangeNameIgnoreCase(String exchangeName);
    public Optional<ExchangeModel> findByExchangeCodeIgnoreCase(String exchangeCode);
}
