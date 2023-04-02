package com.api.hefesto.repository;

import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.hefesto.model.ExchangeModel;

public interface ExchangeRepository extends JpaRepository<ExchangeModel, UUID> {
    
}
