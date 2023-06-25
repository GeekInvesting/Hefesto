package com.api.hefesto.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.hefesto.dto.ListCodeDto;
import com.api.hefesto.model.TicketModel;
import com.api.hefesto.repository.TicketRepository;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public TicketModel saveTicket(TicketModel ticketModel) {
        return ticketRepository.save(ticketModel);
    }

    public List<TicketModel> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<TicketModel> getTicketById(UUID id) {
        return ticketRepository.findById(id);
    }

    public List<ListCodeDto> getAllTicketCode() {
        return ticketRepository.findAllTicketCode();
    }
}
