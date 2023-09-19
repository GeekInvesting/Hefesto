package com.api.hefesto.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_country")
public class CountryModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;
    private String countryName;
    private String countryCode;
    private boolean countryEnabled;
    private boolean countryDeleted;

    @OneToMany(mappedBy = "exchangeCountry")
    private List<ExchangeModel> exchanges;

    public CountryModel() {
    }

    public CountryModel(UUID id, String countryName, String countryCode, boolean countryEnabled, boolean countryDeleted){
        this.id = id;
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.countryEnabled = countryEnabled;
        this.countryDeleted = countryDeleted;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getCountryName() {
        return countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public boolean isCountryEnabled() {
        return countryEnabled;
    }
    public void setCountryEnabled(boolean countryEnabled) {
        this.countryEnabled = countryEnabled;
    }
    public boolean isCountryDeleted() {
        return countryDeleted;
    }
    public void setCountryDeleted(boolean countryDeleted) {
        this.countryDeleted = countryDeleted;
    }

    @Override
    public String toString() {
        return "CountryModel [id=" + id + ", countryName=" + countryName + ", countryCode=" + countryCode
                + ", countryEnabled=" + countryEnabled + ", countryDeleted=" + countryDeleted + "]";
    }

}
