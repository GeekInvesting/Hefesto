package com.api.hefesto.dto;

import java.util.UUID;

public class SubsectorDto {
    
    private UUID id;
    private String subsectorName;
    private boolean subsectorEnabled;
    private boolean subsectorDeleted;
    private String sectorName;
    
    public SubsectorDto() {
    }

    public SubsectorDto(UUID id, String subsectorName, boolean subsectorEnabled, boolean subsectorDeleted,
            String sectorName) {
        this.id = id;
        this.subsectorName = subsectorName;
        this.subsectorEnabled = subsectorEnabled;
        this.subsectorDeleted = subsectorDeleted;
        this.sectorName = sectorName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSubsectorName() {
        return subsectorName;
    }

    public void setSubsectorName(String subsectorName) {
        this.subsectorName = subsectorName;
    }

    public boolean isSubsectorEnabled() {
        return subsectorEnabled;
    }

    public void setSubsectorEnabled(boolean subsectorEnabled) {
        this.subsectorEnabled = subsectorEnabled;
    }

    public boolean isSubsectorDeleted() {
        return subsectorDeleted;
    }

    public void setSubsectorDeleted(boolean subsectorDeleted) {
        this.subsectorDeleted = subsectorDeleted;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    @Override
    public String toString() {
        return "SubsectorDto [id=" + id + ", sectorName=" + sectorName + ", subsectorDeleted=" + subsectorDeleted
                + ", subsectorEnabled=" + subsectorEnabled + ", subsectorName=" + subsectorName + "]";
    }
    
}
