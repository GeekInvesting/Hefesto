package com.api.hefesto.controller;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.hefesto.controller.exception.handler.NotAcceptableException;
import com.api.hefesto.controller.exception.handler.ResourceNotFoundException;
import com.api.hefesto.model.TypeModel;
import com.api.hefesto.service.TypeService;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("hefesto/type")
public class TypeController {
    private static final Logger LOG = LogManager.getLogger(TypeController.class);

    @Autowired
    private TypeService typeService;

    @PostMapping
    public ResponseEntity<Object> createType(@RequestBody TypeModel typeDto) {
        LOG.info("Create Type: " + typeDto.toString());

        if (StringUtils.isBlank(typeDto.getTypeCode())) {
            throw new NotAcceptableException("Type code is required");
        }
        if (StringUtils.isBlank(typeDto.getTypeName())) {
            throw new NotAcceptableException("Type name is required");
        }

        TypeModel typeModel = new TypeModel(
                null,
                typeDto.getTypeCode().toUpperCase().trim(),
                typeDto.getTypeName().toUpperCase().trim(),
                true,
                false);

        TypeModel typeCreate = typeService.saveType(typeModel);

        if (typeCreate == null) {
            throw new NotAcceptableException("Error to create type");
        }

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(typeCreate.getId())
                .toUri();

        return ResponseEntity.created(uri).body(typeCreate);
    }

    @GetMapping("all")
    public ResponseEntity<Object> getAllTypes() {
        LOG.info("Get all types");

        return ResponseEntity.ok(typeService.getAllTypes());
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateType(@Valid @PathVariable UUID id, @RequestBody TypeModel typeDto) {
        LOG.info("Update Type: " + typeDto.toString());

        Optional<TypeModel> typeSearch = typeService.getTypeById(id);
        if (typeSearch.isEmpty()) {
            throw new ResourceNotFoundException("Type Not Found");
        }

        if (StringUtils.isBlank(typeDto.getTypeCode())) {
            throw new NotAcceptableException("Type code is required");
        }
        typeSearch.get().setTypeCode(typeDto.getTypeCode().toUpperCase().trim());

        if (StringUtils.isBlank(typeDto.getTypeName())) {
            throw new NotAcceptableException("Type name is required");
        }
        typeSearch.get().setTypeName(typeDto.getTypeName().toUpperCase().trim());

        typeSearch.get().setTypeEnabled(true);
        typeSearch.get().setTypeDeleted(false);

        TypeModel typeUpdate = typeService.saveType(typeSearch.get());

        if (typeUpdate == null) {
            throw new NotAcceptableException("Error to update type");
        }

        return ResponseEntity.ok().body(typeUpdate);
    }

    @PutMapping("enable/{id}")
    public ResponseEntity<Object> enableType(@PathVariable UUID id) {
        LOG.info("Enable Type: " + id);

        Optional<TypeModel> typeSearch = typeService.getTypeById(id);
        if (typeSearch.isEmpty()) {
            throw new ResourceNotFoundException("Type Not Found");
        }

        typeSearch.get().setTypeEnabled(true);

        TypeModel typeUpdate = typeService.saveType(typeSearch.get());
        if (typeUpdate == null) {
            throw new NotAcceptableException("Error to enable type");
        }

        return ResponseEntity.ok().body(typeUpdate);
    }

    @PutMapping("disable/{id}")
    public ResponseEntity<Object> disableType(@PathVariable UUID id) {
        LOG.info("Disable Type: " + id);

        Optional<TypeModel> typeSearch = typeService.getTypeById(id);
        if (typeSearch.isEmpty()) {
            throw new ResourceNotFoundException("Type Not Found");
        }

        typeSearch.get().setTypeEnabled(false);

        TypeModel typeUpdate = typeService.saveType(typeSearch.get());
        if (typeUpdate == null) {
            throw new NotAcceptableException("Error to disable type");
        }

        return ResponseEntity.ok().body(typeUpdate);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteType(@PathVariable UUID id) {
        LOG.info("Delete Type: " + id);

        Optional<TypeModel> typeSearch = typeService.getTypeById(id);
        if (typeSearch.isEmpty()) {
            throw new ResourceNotFoundException("Type Not Found");
        }

        typeSearch.get().setTypeEnabled(false);
        typeSearch.get().setTypeDeleted(true);

        TypeModel typeUpdate = typeService.saveType(typeSearch.get());
        if (typeUpdate == null) {
            throw new NotAcceptableException("Error to delete type");
        }

        return ResponseEntity.ok().body(typeUpdate);
    }

    @GetMapping("all/code")
    public ResponseEntity<Object> listTypeCode(){
        LOG.info("Get all types code");

        return ResponseEntity.ok(typeService.listTypeCode());
    }
    // TODO: Implements this requests
}
