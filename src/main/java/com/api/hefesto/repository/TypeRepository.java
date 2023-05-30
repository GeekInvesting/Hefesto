package com.api.hefesto.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.hefesto.model.TypeModel;

public interface TypeRepository extends JpaRepository<TypeModel, UUID> {
    
}
