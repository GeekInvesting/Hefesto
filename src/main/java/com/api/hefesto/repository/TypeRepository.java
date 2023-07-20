package com.api.hefesto.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.api.hefesto.dto.TypeCodeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.hefesto.model.TypeModel;

public interface TypeRepository extends JpaRepository<TypeModel, UUID> {
    
    public Optional<TypeModel> findByTypeCodeIgnoreCase(String typeCode);

    @Query("SELECT c.typeCode FROM TypeModel c WHERE c.typeEnabled = true AND c.typeDeleted = false")
    public List<String> listAllTypeCode();

    @Query("SELECT new com.api.hefesto.dto.TypeCodeDto(t.id, t.typeCode) FROM TypeModel t WHERE t.typeEnabled = true AND t.typeDeleted = false order by t.typeCode asc")
    public List<TypeCodeDto> listTypeCode();
}
