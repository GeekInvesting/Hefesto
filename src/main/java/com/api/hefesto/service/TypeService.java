package com.api.hefesto.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
}
