package com.api.hefesto.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.api.hefesto.dto.ListCodeDto;
import com.api.hefesto.dto.TicketDto;
import com.api.hefesto.dto.TicketToZeus;
import com.api.hefesto.model.CompanyModel;
import com.api.hefesto.model.ExchangeModel;
import com.api.hefesto.model.SubsectorModel;
import com.api.hefesto.model.TicketModel;
import com.api.hefesto.model.TypeModel;
import com.api.hefesto.service.CompanyService;
import com.api.hefesto.service.ExchangeService;
import com.api.hefesto.service.RabbitMqService;
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

    @Autowired
    private RabbitMqService mqService;

    @Value("${rabbitmq.queue.ticket.zeus}")
    private String queueToZeus;

    @PostMapping
    public ResponseEntity<Object> createTicket(@RequestBody TicketDto ticketDto) {
        LOG.info("Create Ticket: " + ticketDto.toString());

        if (StringUtils.isBlank(ticketDto.getTicketCode())) {
            throw new NotAcceptableException("Ticket code is required");
        }

        TicketModel ticketModel = new TicketModel(
                ticketDto.getTicketCode().toUpperCase().trim());

        if (StringUtils.isNotBlank(ticketDto.getTicketAlpha())) {
            ticketModel.setTicketAlpha(ticketDto.getTicketAlpha().toUpperCase().trim());
        }

        if (StringUtils.isNotBlank(ticketDto.getTicketCvmCode())) {
            ticketModel.setTicketCvmCode(ticketDto.getTicketCvmCode().toUpperCase().trim());
        }

        if (StringUtils.isNotBlank(ticketDto.getTicketExchangeCode())) {
            Optional<ExchangeModel> exchangeSearch = exchangeService
                    .getExchangeByCode(ticketDto.getTicketExchangeCode());
            if (!exchangeSearch.isPresent()) {
                throw new NotAcceptableException("Exchange not found");
            }

            ticketModel.setTicketExchange(exchangeSearch.get());
        }

        if (StringUtils.isNotBlank(ticketDto.getTicketCompanyName())) {
            Optional<CompanyModel> companySearch = companyService.getCompanyByName(ticketDto.getTicketCompanyName());
            if (!companySearch.isPresent()) {
                throw new NotAcceptableException("Company not found");
            }

            ticketModel.setTicketCompany(companySearch.get());
        }

        if (StringUtils.isNotBlank(ticketDto.getTicketSubsectorName())) {
            Optional<SubsectorModel> subsectorSearch = subsectorService
                    .getSubsectorByName(ticketDto.getTicketSubsectorName());
            if (!subsectorSearch.isPresent()) {
                throw new NotAcceptableException("Subsector not found");
            }

            ticketModel.setTicketSubsector(subsectorSearch.get());
        }

        if (StringUtils.isNotBlank(ticketDto.getTicketTypeCode())) {
            Optional<TypeModel> typeSearch = typeService.getTypeByCode(ticketDto.getTicketTypeCode());
            if (!typeSearch.isPresent()) {
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

        //TODO: Alterar para enviar apenas o ID Type
        TicketToZeus ticketToZeus = new TicketToZeus();
        BeanUtils.copyProperties(ticketCreate, ticketToZeus);
        ticketToZeus.setTicketExchangeCode(ticketCreate.getTicketExchange().getExchangeCode());
        ticketToZeus.setTicketCompanyName(ticketCreate.getTicketCompany().getCompanyName());
        ticketToZeus.setTicketSubsectorName(ticketCreate.getTicketSubsector().getSubsectorName());
        ticketToZeus.setTicketTypeCode(ticketCreate.getTicketType().getTypeCode());
        ticketToZeus.setTicketSectorName(ticketCreate.getTicketSubsector().getSectorModel().getSectorName());
        mqService.sendMessage(queueToZeus, ticketToZeus);

        return ResponseEntity.created(uri).body(ticketCreate);
    }

    @GetMapping("all")
    public ResponseEntity<Object> getAllTickets() {
        LOG.info("Get All Tickets");

        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateTicket(@PathVariable UUID id, @RequestBody TicketDto ticketDto) {
        LOG.info("Update Ticket to: " + ticketDto.toString());

        Optional<TicketModel> ticketSearch = ticketService.getTicketById(id);

        if (!ticketSearch.isPresent()) {
            throw new NotAcceptableException("Ticket not found");
        }

        ticketSearch.get().setTicketAlpha(
                ticketDto.getTicketAlpha() == null
                        ? ticketSearch.get().getTicketAlpha()
                        : ticketDto.getTicketAlpha().toUpperCase().trim());

        ticketSearch.get().setTicketCvmCode(
                ticketDto.getTicketCvmCode() == null
                        ? ticketSearch.get().getTicketCvmCode()
                        : ticketDto.getTicketCvmCode().toUpperCase().trim());

        Optional<CompanyModel> companySearch = companyService.getCompanyByName(ticketDto.getTicketCompanyName());
        ticketSearch.get().setTicketCompany(
                companySearch.isPresent()
                        ? companySearch.get()
                        : ticketSearch.get().getTicketCompany());

        Optional<ExchangeModel> exchangeSearch = exchangeService.getExchangeByCode(ticketDto.getTicketExchangeCode());
        ticketSearch.get().setTicketExchange(
                exchangeSearch.isPresent()
                        ? exchangeSearch.get()
                        : ticketSearch.get().getTicketExchange());

        Optional<TypeModel> typeSearch = typeService.getTypeByCode(ticketDto.getTicketTypeCode());
        ticketSearch.get().setTicketType(
                typeSearch.isPresent()
                        ? typeSearch.get()
                        : ticketSearch.get().getTicketType());

        Optional<SubsectorModel> subsectorSearch = subsectorService
                .getSubsectorByName(ticketDto.getTicketSubsectorName());
        ticketSearch.get().setTicketSubsector(
                subsectorSearch.isPresent()
                        ? subsectorSearch.get()
                        : ticketSearch.get().getTicketSubsector());

        ticketSearch.get().setTicketEnabled(true);
        ticketSearch.get().setTicketDeleted(false);

        TicketModel ticketUpdate = ticketService.saveTicket(ticketSearch.get());

        TicketToZeus ticketToZeus = new TicketToZeus();
        BeanUtils.copyProperties(ticketUpdate, ticketToZeus);
        ticketToZeus.setTicketExchangeCode(ticketUpdate.getTicketExchange().getExchangeCode());
        ticketToZeus.setTicketCompanyName(ticketUpdate.getTicketCompany().getCompanyName());
        ticketToZeus.setTicketSubsectorName(ticketUpdate.getTicketSubsector().getSubsectorName());
        ticketToZeus.setTicketTypeCode(ticketUpdate.getTicketType().getTypeCode());
        ticketToZeus.setTicketSectorName(ticketUpdate.getTicketSubsector().getSectorModel().getSectorName());
        mqService.sendMessage(queueToZeus, ticketToZeus);

        return ResponseEntity.ok(ticketUpdate);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteTicket(@PathVariable UUID id) {
        LOG.info("Delete Ticket: " + id);

        Optional<TicketModel> ticketSearch = ticketService.getTicketById(id);

        if (!ticketSearch.isPresent()) {
            throw new NotAcceptableException("Ticket not found");
        }

        ticketSearch.get().setTicketEnabled(false);
        ticketSearch.get().setTicketDeleted(true);

        TicketModel ticketDelete = ticketService.saveTicket(ticketSearch.get());

        TicketToZeus ticketToZeus = new TicketToZeus();
        BeanUtils.copyProperties(ticketDelete, ticketToZeus);
        ticketToZeus.setTicketExchangeCode(ticketDelete.getTicketExchange().getExchangeCode());
        ticketToZeus.setTicketCompanyName(ticketDelete.getTicketCompany().getCompanyName());
        ticketToZeus.setTicketSubsectorName(ticketDelete.getTicketSubsector().getSubsectorName());
        ticketToZeus.setTicketTypeCode(ticketDelete.getTicketType().getTypeCode());
        ticketToZeus.setTicketSectorName(ticketDelete.getTicketSubsector().getSectorModel().getSectorName());
        mqService.sendMessage(queueToZeus, ticketToZeus);

        return ResponseEntity.ok(ticketDelete);
    }

    @PutMapping("enable/{id}")
    public ResponseEntity<Object> enableTicket(@PathVariable UUID id) {
        LOG.info("Enable Ticket: " + id);

        Optional<TicketModel> ticketSearch = ticketService.getTicketById(id);

        if (!ticketSearch.isPresent()) {
            throw new NotAcceptableException("Ticket not found");
        }

        ticketSearch.get().setTicketEnabled(true);
        ticketSearch.get().setTicketDeleted(false);

        TicketModel ticketEnable = ticketService.saveTicket(ticketSearch.get());

        TicketToZeus ticketToZeus = new TicketToZeus();
        BeanUtils.copyProperties(ticketEnable, ticketToZeus);
        ticketToZeus.setTicketExchangeCode(ticketEnable.getTicketExchange().getExchangeCode());
        ticketToZeus.setTicketCompanyName(ticketEnable.getTicketCompany().getCompanyName());
        ticketToZeus.setTicketSubsectorName(ticketEnable.getTicketSubsector().getSubsectorName());
        ticketToZeus.setTicketTypeCode(ticketEnable.getTicketType().getTypeCode());
        ticketToZeus.setTicketSectorName(ticketEnable.getTicketSubsector().getSectorModel().getSectorName());
        mqService.sendMessage(queueToZeus, ticketToZeus);

        return ResponseEntity.ok(ticketEnable);
    }

    @PutMapping("disable/{id}")
    public ResponseEntity<Object> disableTicket(@PathVariable UUID id) {
        LOG.info("Disable Ticket: " + id);

        Optional<TicketModel> ticketSearch = ticketService.getTicketById(id);

        if (!ticketSearch.isPresent()) {
            throw new NotAcceptableException("Ticket not found");
        }

        ticketSearch.get().setTicketEnabled(false);
        ticketSearch.get().setTicketDeleted(false);

        TicketModel ticketDisable = ticketService.saveTicket(ticketSearch.get());

        TicketToZeus ticketToZeus = new TicketToZeus();

        BeanUtils.copyProperties(ticketDisable, ticketToZeus);
        ticketToZeus.setTicketExchangeCode(ticketDisable.getTicketExchange().getExchangeCode());
        ticketToZeus.setTicketCompanyName(ticketDisable.getTicketCompany().getCompanyName());
        ticketToZeus.setTicketSubsectorName(ticketDisable.getTicketSubsector().getSubsectorName());
        ticketToZeus.setTicketTypeCode(ticketDisable.getTicketType().getTypeCode());
        ticketToZeus.setTicketSectorName(ticketDisable.getTicketSubsector().getSectorModel().getSectorName());
        mqService.sendMessage(queueToZeus, ticketToZeus);

        return ResponseEntity.ok(ticketDisable);
    }

    @GetMapping("code/all")
    public ResponseEntity<Object> getAllTicketByCode() {
        LOG.info("Get All Ticket By Code");

        List<ListCodeDto> ticketList = ticketService.getAllTicketCode();
        LOG.info("Ticket List: " + ticketList.toString());

        return ResponseEntity.ok(ticketList);
    }

}
