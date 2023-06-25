package com.api.hefesto.dto;

import java.io.Serializable;
import java.util.UUID;

public class ListCodeDto implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String ticketCode;

    public ListCodeDto() {
    }

    public ListCodeDto(UUID id, String ticketCode) {
        this.id = id;
        this.ticketCode = ticketCode;
    }

    public UUID getId() {
        return id;
    }

    public String getTicketCode() {
        return ticketCode;
    }
}
