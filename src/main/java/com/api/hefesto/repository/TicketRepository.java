package com.api.hefesto.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.hefesto.model.TicketModel;

public interface TicketRepository extends JpaRepository<TicketModel, UUID> {
    
}
