package com.api.hefesto.dto;

import java.util.UUID;

public class ExchangeDto {
    
    private UUID id;    
	private String exchangeName;
	private String exchangeCode;
	private String exchangeCurrency;
	private String exchangeCountry;
    private boolean exchangeEnabled;
    private boolean exchangeDeleted;

    public ExchangeDto(){}

    public ExchangeDto(UUID id, String exchangeName, String exchangeCode, String exchangeCurrency,
            String exchangeCountry, boolean exchangeEnabled, boolean exchangeDeleted) {
        this.id = id;
        this.exchangeName = exchangeName;
        this.exchangeCode = exchangeCode;
        this.exchangeCurrency = exchangeCurrency;
        this.exchangeCountry = exchangeCountry;
        this.exchangeEnabled = exchangeEnabled;
        this.exchangeDeleted = exchangeDeleted;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getExchangeCountry() {
        return exchangeCountry;
    }

    public void setExchangeCountry(String exchangeCountry) {
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
    public String toString() {
        return "ExchangeDto [id=" + id + ", exchangeName=" + exchangeName + ", exchangeCode=" + exchangeCode
                + ", exchangeCurrency=" + exchangeCurrency + ", exchangeCountry=" + exchangeCountry
                + ", exchangeEnabled=" + exchangeEnabled + ", exchangeDeleted=" + exchangeDeleted + "]";
    }

    
}
