package com.api.hefesto.controller;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.hefesto.controller.exception.handler.DataConflictException;
import com.api.hefesto.controller.exception.handler.ResourceNotFoundException;
import com.api.hefesto.dto.ExchangeDto;
import com.api.hefesto.model.CountryModel;
import com.api.hefesto.model.ExchangeModel;
import com.api.hefesto.service.CountryService;
import com.api.hefesto.service.ExchangeService;

import jakarta.validation.Valid;
import jakarta.ws.rs.NotAcceptableException;

@RestController
@RequestMapping("exchange")
public class ExchangeController {
    private static final Logger LOG = LogManager.getLogger(ExchangeController.class);

    @Autowired
    private ExchangeService exchangeService;

    @Autowired
    private CountryService countryService;

    private ExchangeModel exchangeModel = new ExchangeModel();

    @PostMapping
    public ResponseEntity<Object> createExchange(@Valid @RequestBody ExchangeDto exchangeDto) throws Exception {
        LOG.info("Create exchange: " + exchangeDto.toString());

        if (exchangeDto.getExchangeName() == null) {
            throw new NotAcceptableException("Exchange Name is Required!");
        }
        if (exchangeService.existsByName(exchangeDto.getExchangeName())) {
            throw new DataConflictException("Exchange Name already exists! " + exchangeDto.getExchangeName());
        }
        exchangeModel.setExchangeName(exchangeDto.getExchangeName().trim().toUpperCase());

        if (exchangeDto.getExchangeCode() == null) {
            throw new NotAcceptableException("Exchange Code is Required!");
        }
        if (exchangeService.existsByCode(exchangeDto.getExchangeCode())) {
            throw new DataConflictException("Exchange Code already exists! " + exchangeDto.getExchangeCode());
        }
        exchangeModel.setExchangeCode(exchangeDto.getExchangeCode().trim().toUpperCase());

        if (exchangeDto.getExchangeCountry() == null) {
            throw new NotAcceptableException("Exchange Country is Required!");
        }
        Optional<CountryModel> countryModel = countryService.findByName(exchangeDto.getExchangeCountry());
        if (countryModel.isEmpty()) {
            throw new ResourceNotFoundException("Exchange Country not found! " + exchangeDto.getExchangeCountry());
        }
        if (!countryModel.get().isCountryEnabled()) {
            throw new NotAcceptableException("Exchange Country is not enabled! " + exchangeDto.getExchangeCountry());
        }
        if (countryModel.get().isCountryDeleted()) {
            throw new NotAcceptableException("Exchange Country is deleted! " + exchangeDto.getExchangeCountry());
        }
        exchangeModel.setExchangeCountry(countryModel.get());

        // TODO: Adicionar validação de moeda
        exchangeModel.setExchangeCurrency(exchangeDto.getExchangeCurrency().trim().toUpperCase());

        exchangeModel.setExchangeEnabled(true);
        exchangeModel.setExchangeDeleted(false);

        ExchangeModel exchangeCreated = exchangeService.saveExchange(exchangeModel);

        if (exchangeCreated == null) {
            throw new Exception("Created Exchange as failed!");
        }

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(exchangeCreated.getId())
                .toUri();

        return ResponseEntity.created(uri).body(exchangeCreated);
    }

    @GetMapping("all")
    public ResponseEntity<Object> getAllExchange() {
        LOG.info("Get all exchange");
        return ResponseEntity.status(HttpStatus.OK).body(exchangeService.findAllExchange());
    }

    @GetMapping
    public ResponseEntity<Object> getAllExchangeNotDeleted() {
        LOG.info("Get all exchange not deleted");
        return ResponseEntity.status(HttpStatus.OK).body(exchangeService.findAllExchangeNotDeleted());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getExchangeById(@PathVariable UUID id) {
        LOG.info("Get exchange by id: " + id);
        Optional<ExchangeModel> exchangeModel = exchangeService.findById(id);
        if (exchangeModel.isEmpty()) {
            throw new ResourceNotFoundException("Exchange not found! " + id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(exchangeModel.get());
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateExchange(
            @Valid @RequestBody ExchangeDto exchangeDto,
            @PathVariable UUID id) {
        LOG.info("Exchange Updating: " + exchangeDto.toString());

        Optional<ExchangeModel> exchangeModel = exchangeService.findById(id);
        if (exchangeModel.isEmpty()) {
            throw new ResourceNotFoundException("Exchange not found! " + id);
        }

        if (exchangeDto.getExchangeName() != null) {
            exchangeModel.get().setExchangeName(exchangeDto.getExchangeName().trim().toUpperCase());
        }
        if (exchangeDto.getExchangeCode() != null) {
            exchangeModel.get().setExchangeCode(exchangeDto.getExchangeCode().trim().toUpperCase());
        }
        if (exchangeDto.getExchangeCountry() != null) {
            Optional<CountryModel> countryModel = countryService.findByName(exchangeDto.getExchangeCountry());
            if (countryModel.isEmpty()) {
                throw new ResourceNotFoundException("Exchange Country not found! " + exchangeDto.getExchangeCountry());
            }
            if (!countryModel.get().isCountryEnabled()) {
                throw new NotAcceptableException(
                        "Exchange Country is not enabled! " + exchangeDto.getExchangeCountry());
            }
            if (countryModel.get().isCountryDeleted()) {
                throw new NotAcceptableException("Exchange Country is deleted! " + exchangeDto.getExchangeCountry());
            }
            exchangeModel.get().setExchangeCountry(countryModel.get());
        }
        if (exchangeDto.getExchangeCurrency() != null) {
            exchangeModel.get().setExchangeCurrency(exchangeDto.getExchangeCurrency().trim().toUpperCase());
        }

        exchangeModel.get().setExchangeEnabled(true);
        exchangeModel.get().setExchangeDeleted(false);

        ExchangeModel exchangeUpdate = exchangeService.saveExchange(exchangeModel.get());

        return ResponseEntity.status(HttpStatus.OK).body(exchangeUpdate);
    }

    @PutMapping("enable/{id}")
    public ResponseEntity<Object> enableExchange(@PathVariable UUID id) {
        LOG.info("Enable exchange: " + id);

        Optional<ExchangeModel> exchangeModel = exchangeService.findById(id);
        if (exchangeModel.isEmpty()) {
            throw new ResourceNotFoundException("Exchange not found! " + id);
        }

        exchangeModel.get().setExchangeEnabled(true);
        exchangeModel.get().setExchangeDeleted(false);

        ExchangeModel exchangeUpdate = exchangeService.saveExchange(exchangeModel.get());

        return ResponseEntity.status(HttpStatus.OK).body(exchangeUpdate);
    }

    @PutMapping("disable/{id}")
    public ResponseEntity<Object> disableExchange(@PathVariable UUID id) {
        LOG.info("Disable exchange: " + id);

        Optional<ExchangeModel> exchangeModel = exchangeService.findById(id);
        if (exchangeModel.isEmpty()) {
            throw new ResourceNotFoundException("Exchange not found! " + id);
        }

        exchangeModel.get().setExchangeEnabled(false);
        exchangeModel.get().setExchangeDeleted(false);

        ExchangeModel exchangeUpdate = exchangeService.saveExchange(exchangeModel.get());

        return ResponseEntity.status(HttpStatus.OK).body(exchangeUpdate);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteExchange(@PathVariable UUID id) {
        LOG.info("Delete exchange: " + id);

        Optional<ExchangeModel> exchangeModel = exchangeService.findById(id);
        if (exchangeModel.isEmpty()) {
            throw new ResourceNotFoundException("Exchange not found! " + id);
        }

        exchangeModel.get().setExchangeEnabled(false);
        exchangeModel.get().setExchangeDeleted(true);

        ExchangeModel exchangeUpdate = exchangeService.saveExchange(exchangeModel.get());

        return ResponseEntity.status(HttpStatus.OK).body(exchangeUpdate);
    }
}
