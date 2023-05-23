package com.api.hefesto.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_SUBSECTOR")
public class SubsectorModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String subsectorName;
    private boolean subsectorEnabled;
    private boolean subsectorDeleted;
    private SectorModel sectorModel;

    public SubsectorModel() {
    }

    public SubsectorModel(UUID id, String subsectorName, boolean subsectorEnabled, boolean subsectorDeleted,
            SectorModel sectorModel) {
        this.id = id;
        this.subsectorName = subsectorName;
        this.subsectorEnabled = subsectorEnabled;
        this.subsectorDeleted = subsectorDeleted;
        this.sectorModel = sectorModel;
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

    public SectorModel getSectorModel() {
        return sectorModel;
    }

    public void setSectorModel(SectorModel sectorModel) {
        this.sectorModel = sectorModel;
    }

    @Override
    public String toString() {
        return "SubsectorModel{" +
                "id=" + id +
                ", subsectorName='" + subsectorName + '\'' +
                ", subsectorEnabled=" + subsectorEnabled +
                ", subsectorDeleted=" + subsectorDeleted +
                ", sectorModel=" + sectorModel +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        SubsectorModel that = (SubsectorModel) o;

        if (subsectorEnabled != that.subsectorEnabled)
            return false;
        if (subsectorDeleted != that.subsectorDeleted)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null)
            return false;
        if (subsectorName != null ? !subsectorName.equals(that.subsectorName) : that.subsectorName != null)
            return false;
        return sectorModel != null ? sectorModel.equals(that.sectorModel) : that.sectorModel == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (subsectorName != null ? subsectorName.hashCode() : 0);
        result = 31 * result + (subsectorEnabled ? 1 : 0);
        result = 31 * result + (subsectorDeleted ? 1 : 0);
        result = 31 * result + (sectorModel != null ? sectorModel.hashCode() : 0);
        return result;
    }
}
