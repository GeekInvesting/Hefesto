package com.api.hefesto.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "TB_TYPE")
public class TypeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID typeId;
    private String typeCode;
    private String typeName;
    private boolean typeEnabled;
    private boolean typeDeleted;

    public TypeModel() {
    }

    public TypeModel(UUID typeId, String typeCode, String typeName, boolean typeEnabled, boolean typeDeleted) {
        this.typeId = typeId;
        this.typeCode = typeCode;
        this.typeName = typeName;
        this.typeEnabled = typeEnabled;
        this.typeDeleted = typeDeleted;
    }

    public UUID getId() {
        return typeId;
    }

    public void setId(UUID typeId) {
        this.typeId = typeId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public boolean isTypeEnabled() {
        return typeEnabled;
    }

    public void setTypeEnabled(boolean typeEnabled) {
        this.typeEnabled = typeEnabled;
    }

    public boolean isTypeDeleted() {
        return typeDeleted;
    }

    public void setTypeDeleted(boolean typeDeleted) {
        this.typeDeleted = typeDeleted;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String type) {
        this.typeName = type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((typeId == null) ? 0 : typeId.hashCode());
        result = prime * result + ((typeCode == null) ? 0 : typeCode.hashCode());
        result = prime * result + ((typeName == null) ? 0 : typeName.hashCode());
        result = prime * result + (typeEnabled ? 1231 : 1237);
        result = prime * result + (typeDeleted ? 1231 : 1237);
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
        TypeModel other = (TypeModel) obj;
        if (typeId == null) {
            if (other.typeId != null)
                return false;
        } else if (!typeId.equals(other.typeId))
            return false;
        if (typeCode == null) {
            if (other.typeCode != null)
                return false;
        } else if (!typeCode.equals(other.typeCode))
            return false;
        if (typeName == null) {
            if (other.typeName != null)
                return false;
        } else if (!typeName.equals(other.typeName))
            return false;
        if (typeEnabled != other.typeEnabled)
            return false;
        if (typeDeleted != other.typeDeleted)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TypeModel [typeId=" + typeId + ", typeCode=" + typeCode + ", typeName=" + typeName + ", typeEnabled="
                + typeEnabled + ", typeDeleted=" + typeDeleted + "]";
    }

}
