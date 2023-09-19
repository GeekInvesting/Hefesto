package com.api.hefesto.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_SECTOR")
public class SectorModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID sectorId;

    @Column(nullable = false)
    private String sectorName;

    @Column
    private boolean sectorEnabled;

    @Column
    private boolean sectorDeleted;

    @OneToMany(mappedBy = "sectorModel")
    private List<SubsectorModel> subsectors;

    public SectorModel() {
    }

    public SectorModel(UUID sectorId, String sectorName, boolean sectorEnabled, boolean sectorDeleted) {
        this.sectorId = sectorId;
        this.sectorName = sectorName;
        this.sectorEnabled = sectorEnabled;
        this.sectorDeleted = sectorDeleted;
    }

    public UUID getId() {
        return sectorId;
    }

    public void setId(UUID sectorId) {
        this.sectorId = sectorId;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public boolean isSectorEnabled() {
        return sectorEnabled;
    }

    public void setSectorEnabled(boolean sectorEnabled) {
        this.sectorEnabled = sectorEnabled;
    }

    public boolean isSectorDeleted() {
        return sectorDeleted;
    }

    public void setSectorDeleted(boolean sectorDeleted) {
        this.sectorDeleted = sectorDeleted;
    }

    @Override
    public String toString() {
        return "SectorModel [sectorId=" + sectorId + ", sectorName=" + sectorName + ", sectorEnabled=" + sectorEnabled
                + ", sectorDeleted=" + sectorDeleted + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sectorId == null) ? 0 : sectorId.hashCode());
        result = prime * result + ((sectorName == null) ? 0 : sectorName.hashCode());
        result = prime * result + (sectorEnabled ? 1231 : 1237);
        result = prime * result + (sectorDeleted ? 1231 : 1237);
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
        SectorModel other = (SectorModel) obj;
        if (sectorId == null) {
            if (other.sectorId != null)
                return false;
        } else if (!sectorId.equals(other.sectorId))
            return false;
        if (sectorName == null) {
            if (other.sectorName != null)
                return false;
        } else if (!sectorName.equals(other.sectorName))
            return false;
        if (sectorEnabled != other.sectorEnabled)
            return false;
        if (sectorDeleted != other.sectorDeleted)
            return false;
        return true;
    }
}
