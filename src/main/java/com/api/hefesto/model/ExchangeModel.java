package com.api.hefesto.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_EXCHANGE")
public class ExchangeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID exchangeId;

    @Column(nullable = false, length = 50)
    private String exchangeName;

    @Column(nullable = false, length = 5)
    private String exchangeCode;

    @Column(length = 3)
    private String exchangeCurrency;

    @JoinColumn
    private CountryModel exchangeCountry;

    @Column
    private boolean exchangeEnabled;

    @Column
    private boolean exchangeDeleted;

    public ExchangeModel() {
    }

    public ExchangeModel(UUID exchangeId, String exchangeName, String exchangeCode, String exchangeCurrency,
            CountryModel exchangeCountry, boolean exchangeEnabled, boolean exchangeDeleted) {
        this.exchangeId = exchangeId;
        this.exchangeName = exchangeName;
        this.exchangeCode = exchangeCode;
        this.exchangeCurrency = exchangeCurrency;
        this.exchangeCountry = exchangeCountry;
        this.exchangeEnabled = exchangeEnabled;
        this.exchangeDeleted = exchangeDeleted;
    }

    public UUID getId() {
        return exchangeId;
    }

    public void setId(UUID exchangeId) {
        this.exchangeId = exchangeId;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    public String getExchangeCurrency() {
        return exchangeCurrency;
    }

    public void setExchangeCurrency(String exchangeCurrency) {
        this.exchangeCurrency = exchangeCurrency;
    }

    public CountryModel getExchangeCountry() {
        return exchangeCountry;
    }

    public void setExchangeCountry(CountryModel exchangeCountry) {
        this.exchangeCountry = exchangeCountry;
    }

    public boolean isExchangeEnabled() {
        return exchangeEnabled;
    }

    public void setExchangeEnabled(boolean exchangeEnabled) {
        this.exchangeEnabled = exchangeEnabled;
    }

    public boolean isExchangeDeleted() {
        return exchangeDeleted;
    }

    public void setExchangeDeleted(boolean exchangeDeleted) {
        this.exchangeDeleted = exchangeDeleted;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((exchangeId == null) ? 0 : exchangeId.hashCode());
        result = prime * result + ((exchangeName == null) ? 0 : exchangeName.hashCode());
        result = prime * result + ((exchangeCode == null) ? 0 : exchangeCode.hashCode());
        result = prime * result + ((exchangeCurrency == null) ? 0 : exchangeCurrency.hashCode());
        result = prime * result + ((exchangeCountry == null) ? 0 : exchangeCountry.hashCode());
        result = prime * result + (exchangeEnabled ? 1231 : 1237);
        result = prime * result + (exchangeDeleted ? 1231 : 1237);
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
        ExchangeModel other = (ExchangeModel) obj;
        if (exchangeId == null) {
            if (other.exchangeId != null)
                return false;
        } else if (!exchangeId.equals(other.exchangeId))
            return false;
        if (exchangeName == null) {
            if (other.exchangeName != null)
                return false;
        } else if (!exchangeName.equals(other.exchangeName))
            return false;
        if (exchangeCode == null) {
            if (other.exchangeCode != null)
                return false;
        } else if (!exchangeCode.equals(other.exchangeCode))
            return false;
        if (exchangeCurrency == null) {
            if (other.exchangeCurrency != null)
                return false;
        } else if (!exchangeCurrency.equals(other.exchangeCurrency))
            return false;
        if (exchangeCountry == null) {
            if (other.exchangeCountry != null)
                return false;
        } else if (!exchangeCountry.equals(other.exchangeCountry))
            return false;
        if (exchangeEnabled != other.exchangeEnabled)
            return false;
        if (exchangeDeleted != other.exchangeDeleted)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ExchangeModel [exchangeId=" + exchangeId + ", exchangeName=" + exchangeName + ", exchangeCode=" + exchangeCode
                + ", exchangeCurrency=" + exchangeCurrency + ", exchangeCountry=" + exchangeCountry
                + ", exchangeEnabled=" + exchangeEnabled + ", exchangeDeleted=" + exchangeDeleted + "]";
    }

}
