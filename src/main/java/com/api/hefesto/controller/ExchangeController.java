package com.api.hefesto.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.hefesto.dto.ExchangeDto;
import com.api.hefesto.model.ExchangeModel;
import com.api.hefesto.service.ExchangeService;

import jakarta.validation.Valid;
import jakarta.ws.rs.NotAcceptableException;

@RestController
@RequestMapping("exchange")
public class ExchangeController {
    private static final Logger LOG = LogManager.getLogger(ExchangeController.class);

    @Autowired
    private ExchangeService exchangeService;

    private ExchangeModel exchangeModel;

    @PostMapping
    public ResponseEntity<Object> createExchange(@Valid @RequestBody ExchangeDto exchangeDto){
        LOG.info("Create exchange: " + exchangeDto.toString());

        if(exchangeDto.getExchangeName() == null){
            throw new NotAcceptableException("Exchange Name is Required!");
        }

        //TODO: Finalizar validações

        return ResponseEntity.created(null).body(null);
    }
}
