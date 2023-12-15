package com.api.hefesto.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.api.hefesto.dto.TypeCodeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.hefesto.model.TypeModel;
import com.api.hefesto.repository.TypeRepository;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    public TypeModel saveType(TypeModel typeModel) {
        return typeRepository.save(typeModel);
    }

    public List<TypeModel> getAllTypes() {
        return typeRepository.findAll();
    }

    public Optional<TypeModel> getTypeById(UUID id){
        return typeRepository.findById(id);
    }

    public Optional<TypeModel> getTypeByCode(String typeCpde){
        return typeRepository.findByTypeCodeIgnoreCase(typeCpde);
    }

    public List<String> listTypeCode(){
        return typeRepository.listAllTypeCode();
    }

    public List<TypeCodeDto> listTypeAutocomplete(){
        return typeRepository.listTypeCode();
    }

    public TypeModel saveTypeByConsumer(TypeModel typeModel){
        TypeModel type = typeRepository.findByTypeCodeIgnoreCase(typeModel.getTypeCode()).orElse(null);
        return Objects.requireNonNullElseGet(type, () -> typeRepository.save(new TypeModel(null,
                typeModel.getTypeCode().toUpperCase(),
                typeModel.getTypeName().toUpperCase(),
                true,
                false)));
    }
}
