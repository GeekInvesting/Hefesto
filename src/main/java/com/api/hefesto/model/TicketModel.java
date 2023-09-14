package com.api.hefesto.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Persistent;

@Entity
@Table(name = "TB_TICKET")
public class TicketModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String ticketCode;
    private CompanyModel ticketCompany;
    private TypeModel ticketType;
    private SubsectorModel ticketSubsector;
    private ExchangeModel ticketExchange;
    private String ticketAlpha;
    private String ticketCvmCode;
    private String currency;
    private String region;
    private boolean ticketEnabled;
    private boolean ticketDeleted;

    public TicketModel() {
    }

    public TicketModel(UUID id, String ticketCode, CompanyModel ticketCompany, TypeModel ticketType, SubsectorModel ticketSubsector,
            ExchangeModel ticketExchange, String ticketAlpha, String ticketCvmCode, boolean ticketEnabled,
            boolean ticketDeleted, String currency, String region) {
        this.id = id;
        this.ticketCode = ticketCode;
        this.ticketCompany = ticketCompany;
        this.ticketType = ticketType;
        this.ticketSubsector = ticketSubsector;
        this.ticketExchange = ticketExchange;
        this.ticketAlpha = ticketAlpha;
        this.ticketCvmCode = ticketCvmCode;
        this.ticketEnabled = ticketEnabled;
        this.ticketDeleted = ticketDeleted;
        this.currency = currency;
        this.region = region;
    }

    public TicketModel(String ticketCode){
        this.ticketCode = ticketCode;
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

    public CompanyModel getTicketCompany() {
        return ticketCompany;
    }

    public void setTicketCompany(CompanyModel ticketCompany) {
        this.ticketCompany = ticketCompany;
    }

    public TypeModel getTicketType() {
        return ticketType;
    }

    public void setTicketType(TypeModel ticketType) {
        this.ticketType = ticketType;
    }

    public SubsectorModel getTicketSubsector() {
        return ticketSubsector;
    }

    public void setTicketSubsector(SubsectorModel ticketSubsector) {
        this.ticketSubsector = ticketSubsector;
    }

    public ExchangeModel getTicketExchange() {
        return ticketExchange;
    }

    public void setTicketExchange(ExchangeModel ticketExchange) {
        this.ticketExchange = ticketExchange;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((ticketCode == null) ? 0 : ticketCode.hashCode());
        result = prime * result + ((ticketCompany == null) ? 0 : ticketCompany.hashCode());
        result = prime * result + ((ticketType == null) ? 0 : ticketType.hashCode());
        result = prime * result + ((ticketSubsector == null) ? 0 : ticketSubsector.hashCode());
        result = prime * result + ((ticketExchange == null) ? 0 : ticketExchange.hashCode());
        result = prime * result + ((ticketAlpha == null) ? 0 : ticketAlpha.hashCode());
        result = prime * result + ((ticketCvmCode == null) ? 0 : ticketCvmCode.hashCode());
        result = prime * result + (ticketEnabled ? 1231 : 1237);
        result = prime * result + (ticketDeleted ? 1231 : 1237);
        result = prime * result + ((currency == null) ? 0 : currency.hashCode());
        result = prime * result + ((region == null) ? 0 : region.hashCode());
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
        TicketModel other = (TicketModel) obj;
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
        if (ticketCompany == null) {
            if (other.ticketCompany != null)
                return false;
        } else if (!ticketCompany.equals(other.ticketCompany))
            return false;
        if (ticketType == null) {
            if (other.ticketType != null)
                return false;
        } else if (!ticketType.equals(other.ticketType))
            return false;
        if (ticketSubsector == null) {
            if (other.ticketSubsector != null)
                return false;
        } else if (!ticketSubsector.equals(other.ticketSubsector))
            return false;
        if (ticketExchange == null) {
            if (other.ticketExchange != null)
                return false;
        } else if (!ticketExchange.equals(other.ticketExchange))
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
        if (currency == null) {
            if (other.currency != null)
                return false;
        } else if (!currency.equals(other.currency)){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TicketModel [id=" + id + ", ticketCode=" + ticketCode + ", ticketCompany=" + ticketCompany
                + ", ticketType=" + ticketType + ", ticketSubsector=" + ticketSubsector + ", ticketExchange="
                + ticketExchange + ", ticketAlpha=" + ticketAlpha + ", ticketCvmCode=" + ticketCvmCode
                + ", currency=" + currency + ", region=" + region + ", ticketEnabled=" + ticketEnabled + ", ticketDeleted=" + ticketDeleted + "]";
    }

}
