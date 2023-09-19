package com.api.hefesto.repository;

import java.util.List;
import java.util.UUID;

import com.api.hefesto.dto.TicketPriceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.hefesto.dto.ListCodeDto;
import com.api.hefesto.model.TicketModel;

public interface TicketRepository extends JpaRepository<TicketModel, UUID> {
    
    @Query("SELECT new com.api.hefesto.dto.ListCodeDto(t.id, t.ticketCode) FROM TicketModel t WHERE t.ticketEnabled = true AND t.ticketDeleted = false order by t.ticketCode asc")
    public List<ListCodeDto> findAllTicketCode();

    @Query("SELECT new com.api.hefesto.dto.TicketPriceDto(t.id, t.ticketCode, t.ticketExchange.exchangeCode) FROM TicketModel t WHERE t.ticketEnabled = true AND t.ticketDeleted = false order by t.ticketCode asc")
    public List<TicketPriceDto> findAllTicketPrice();
}
