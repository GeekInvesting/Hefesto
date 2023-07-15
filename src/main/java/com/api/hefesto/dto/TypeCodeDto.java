package com.api.hefesto.dto;

import java.io.Serializable;
import java.util.UUID;

public class TypeCodeDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String typeCode;

    public TypeCodeDto() {
    }

    public TypeCodeDto(UUID id, String typeCode) {
        this.id = id;
        this.typeCode = typeCode;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    @Override
    public String toString() {
        return "TypeCodeDto{" +
                "id=" + id +
                ", typeCode='" + typeCode + '\'' +
                '}';
    }
}
