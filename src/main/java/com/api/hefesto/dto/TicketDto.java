package com.api.hefesto.dto;

import java.io.Serializable;
import java.util.UUID;

public class TicketDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String ticketCode;
    private String ticketCompanyName;
    private String ticketTypeCode;
    private String ticketSubsectorName;
    private String ticketExchangeCode;
    private String ticketAlpha;
    private String ticketCvmCode;
    private boolean ticketEnabled;
    private boolean ticketDeleted;

    public TicketDto() {
    }

    public TicketDto(UUID id, String ticketCode, String ticketCompanyName, String ticketTypeCode, String ticketSubsectorName,
            String ticketExchangeCode, String ticketAlpha, String ticketCvmCode, boolean ticketEnabled,
            boolean ticketDeleted) {
        this.id = id;
        this.ticketCode = ticketCode;
        this.ticketCompanyName = ticketCompanyName;
        this.ticketTypeCode = ticketTypeCode;
        this.ticketSubsectorName = ticketSubsectorName;
        this.ticketExchangeCode = ticketExchangeCode;
        this.ticketAlpha = ticketAlpha;
        this.ticketCvmCode = ticketCvmCode;
        this.ticketEnabled = ticketEnabled;
        this.ticketDeleted = ticketDeleted;
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

    public String getTicketExchangeCode() {
        return ticketExchangeCode;
    }

    public void setTicketExchangeCode(String ticketExchangeCode) {
        this.ticketExchangeCode = ticketExchangeCode;
    }

    public String getTicketAlpha() {
        return ticketAlpha;
    }

    public void setTicketAlpha(String ticketAlpha) {
        this.ticketAlpha = ticketAlpha;
    }

    public String getTicketCvmCode() {
        return ticketCvmCode;
    }

    public void setTicketCvmCode(String ticketCvmCode) {
        this.ticketCvmCode = ticketCvmCode;
    }

    public boolean isTicketEnabled() {
        return ticketEnabled;
    }

    public void setTicketEnabled(boolean ticketEnabled) {
        this.ticketEnabled = ticketEnabled;
    }

    public boolean isTicketDeleted() {
        return ticketDeleted;
    }

    public void setTicketDeleted(boolean ticketDeleted) {
        this.ticketDeleted = ticketDeleted;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((ticketCode == null) ? 0 : ticketCode.hashCode());
        result = prime * result + ((ticketCompanyName == null) ? 0 : ticketCompanyName.hashCode());
        result = prime * result + ((ticketTypeCode == null) ? 0 : ticketTypeCode.hashCode());
        result = prime * result + ((ticketSubsectorName == null) ? 0 : ticketSubsectorName.hashCode());
        result = prime * result + ((ticketExchangeCode == null) ? 0 : ticketExchangeCode.hashCode());
        result = prime * result + ((ticketAlpha == null) ? 0 : ticketAlpha.hashCode());
        result = prime * result + ((ticketCvmCode == null) ? 0 : ticketCvmCode.hashCode());
        result = prime * result + (ticketEnabled ? 1231 : 1237);
        result = prime * result + (ticketDeleted ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TicketDto other = (TicketDto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (ticketCode == null) {
            if (other.ticketCode != null)
                return false;
        } else if (!ticketCode.equals(other.ticketCode))
            return false;
        if (ticketCompanyName == null) {
            if (other.ticketCompanyName != null)
                return false;
        } else if (!ticketCompanyName.equals(other.ticketCompanyName))
            return false;
        if (ticketTypeCode == null) {
            if (other.ticketTypeCode != null)
                return false;
        } else if (!ticketTypeCode.equals(other.ticketTypeCode))
            return false;
        if (ticketSubsectorName == null) {
            if (other.ticketSubsectorName != null)
                return false;
        } else if (!ticketSubsectorName.equals(other.ticketSubsectorName))
            return false;
        if (ticketExchangeCode == null) {
            if (other.ticketExchangeCode != null)
                return false;
        } else if (!ticketExchangeCode.equals(other.ticketExchangeCode))
            return false;
        if (ticketAlpha == null) {
            if (other.ticketAlpha != null)
                return false;
        } else if (!ticketAlpha.equals(other.ticketAlpha))
            return false;
        if (ticketCvmCode == null) {
            if (other.ticketCvmCode != null)
                return false;
        } else if (!ticketCvmCode.equals(other.ticketCvmCode))
            return false;
        if (ticketEnabled != other.ticketEnabled)
            return false;
        if (ticketDeleted != other.ticketDeleted)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TicketDto [id=" + id + ", ticketCode=" + ticketCode + ", ticketCompanyName=" + ticketCompanyName
                + ", ticketTypeCode=" + ticketTypeCode + ", ticketSubsectorName=" + ticketSubsectorName
                + ", ticketExchangeCode=" + ticketExchangeCode + ", ticketAlpha=" + ticketAlpha + ", ticketCvmCode="
                + ticketCvmCode + ", ticketEnabled=" + ticketEnabled + ", ticketDeleted=" + ticketDeleted + "]";
    }
    
}
