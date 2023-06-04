package com.api.hefesto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.hefesto.model.TicketModel;
import com.api.hefesto.repository.TicketRepository;

@Service
public class TicketService {
    
    @Autowired
    private TicketRepository ticketRepository;

    public TicketModel saveTicket(TicketModel ticketModel){
        return ticketRepository.save(ticketModel);
    }
    
}
