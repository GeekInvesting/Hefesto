package com.api.hefesto.dto;

import java.io.Serializable;
import java.util.UUID;

public class TicketPriceDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private String ticketCode;
    private String ticketExchangeCode;

    public TicketPriceDto() {
    }

    public TicketPriceDto(UUID id, String ticketCode, String ticketExchangeCode) {
        this.id = id;
        this.ticketCode = ticketCode;
        this.ticketExchangeCode = ticketExchangeCode;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public String getTicketExchangeCode() {
        return ticketExchangeCode;
    }

    public void setTicketExchangeCode(String ticketExchangeCode) {
        this.ticketExchangeCode = ticketExchangeCode;
    }

    @Override
    public String toString() {
        return "TicketPriceDto{" +
                "id=" + id +
                ", ticketCode='" + ticketCode + '\'' +
                ", ticketExchangeCode='" + ticketExchangeCode + '\'' +
                '}';
    }
}
