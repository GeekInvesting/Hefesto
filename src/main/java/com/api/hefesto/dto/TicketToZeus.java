package com.api.hefesto.dto;

import java.io.Serializable;
import java.util.UUID;

public class TicketToZeus implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String ticketCode;
    private String ticketCompanyName;
    private String ticketTypeCode;
    private String ticketSubsectorName;
    private String ticketSectorName;
    private String ticketExchangeCode;

    public TicketToZeus() {
    }

    public TicketToZeus(UUID id, String ticketCode, String ticketCompanyName, String ticketTypeCode,
            String ticketSubsectorName, String ticketSectorName, String ticketExchangeCode) {
        this.id = id;
        this.ticketCode = ticketCode;
        this.ticketCompanyName = ticketCompanyName;
        this.ticketTypeCode = ticketTypeCode;
        this.ticketSubsectorName = ticketSubsectorName;
        this.ticketSectorName = ticketSectorName;
        this.ticketExchangeCode = ticketExchangeCode;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
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

    public String getTicketCompanyName() {
        return ticketCompanyName;
    }

    public void setTicketCompanyName(String ticketCompanyName) {
        this.ticketCompanyName = ticketCompanyName;
    }

    public String getTicketTypeCode() {
        return ticketTypeCode;
    }

    public void setTicketTypeCode(String ticketTypeCode) {
        this.ticketTypeCode = ticketTypeCode;
    }

    public String getTicketSubsectorName() {
        return ticketSubsectorName;
    }

    public void setTicketSubsectorName(String ticketSubsectorName) {
        this.ticketSubsectorName = ticketSubsectorName;
    }

    public String getTicketSectorName() {
        return ticketSectorName;
    }

    public void setTicketSectorName(String ticketSectorName) {
        this.ticketSectorName = ticketSectorName;
    }

    public String getTicketExchangeCode() {
        return ticketExchangeCode;
    }

    public void setTicketExchangeCode(String ticketExchangeCode) {
        this.ticketExchangeCode = ticketExchangeCode;
    }

    
}
