package com.api.hefesto.controller;

import java.net.URI;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.hefesto.controller.exception.handler.NotAcceptableException;
import com.api.hefesto.dto.TicketDto;
import com.api.hefesto.model.CompanyModel;
import com.api.hefesto.model.ExchangeModel;
import com.api.hefesto.model.SubsectorModel;
import com.api.hefesto.model.TicketModel;
import com.api.hefesto.model.TypeModel;
import com.api.hefesto.service.CompanyService;
import com.api.hefesto.service.ExchangeService;
import com.api.hefesto.service.SubsectorService;
import com.api.hefesto.service.TicketService;
import com.api.hefesto.service.TypeService;

@CrossOrigin("${cors.origin}")
@RequestMapping("hefesto/ticket")
@RestController
public class TicketController {
    private static final Logger LOG = LogManager.getLogger(TicketController.class);

    @Autowired
    private TicketService ticketService;

    @Autowired 
    private ExchangeService exchangeService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private SubsectorService subsectorService;

    @Autowired
    private TypeService typeService;

    @PostMapping
    public ResponseEntity<Object> createTicket(@RequestBody TicketDto ticketDto) {
        LOG.info("Create Ticket: " + ticketDto.toString());

        if(StringUtils.isBlank(ticketDto.getTicketCode())){
            throw new NotAcceptableException("Ticket code is required");
        }

        TicketModel ticketModel = new TicketModel(
                ticketDto.getTicketCode().toUpperCase().trim());
        
        if(StringUtils.isNotBlank(ticketDto.getTicketAlpha())){
            ticketModel.setTicketAlpha(ticketDto.getTicketAlpha().toUpperCase().trim());
        }

        if(StringUtils.isNotBlank(ticketDto.getTicketCvmCode())){
            ticketModel.setTicketCvmCode(ticketDto.getTicketCvmCode().toUpperCase().trim());
        }

        if(StringUtils.isNotBlank(ticketDto.getTicketExchangeCode())) {
            Optional<ExchangeModel> exchangeSearch = exchangeService.getExchangeByCode(ticketDto.getTicketExchangeCode());
            if(!exchangeSearch.isPresent()){
                throw new NotAcceptableException("Exchange not found");
            }

            ticketModel.setTicketExchange(exchangeSearch.get());
        }

        if(StringUtils.isNotBlank(ticketDto.getTicketCompanyName())){
            Optional<CompanyModel> companySearch = companyService.getCompanyByName(ticketDto.getTicketCompanyName());
            if(!companySearch.isPresent()){
                throw new NotAcceptableException("Company not found");
            }

            ticketModel.setTicketCompany(companySearch.get());
        }

        if(StringUtils.isNotBlank(ticketDto.getTicketSubsectorName())){
            Optional<SubsectorModel> subsectorSearch = subsectorService.getSubsectorByName(ticketDto.getTicketSubsectorName());
            if(!subsectorSearch.isPresent()){
                throw new NotAcceptableException("Subsector not found");
            }

            ticketModel.setTicketSubsector(subsectorSearch.get());
        }

        if(StringUtils.isNotBlank(ticketDto.getTicketTypeCode())){
            Optional<TypeModel> typeSearch = typeService.getTypeByCode(ticketDto.getTicketTypeCode());
            if(!typeSearch.isPresent()){
                throw new NotAcceptableException("Type not found");
            }

            ticketModel.setTicketType(typeSearch.get());
        }

        ticketModel.setTicketEnabled(true);
        ticketModel.setTicketDeleted(false);

        TicketModel ticketCreate = ticketService.saveTicket(ticketModel);

        URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(ticketCreate.getId())
        .toUri();

        //TODO: Implement msg ticketToZeus

        return ResponseEntity.created(uri).body(ticketCreate);
    }


}
