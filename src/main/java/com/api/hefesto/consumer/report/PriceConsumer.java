package com.api.hefesto.consumer.report;

import com.api.hefesto.dto.ListCodeDto;
import com.api.hefesto.dto.ReportDto;
import com.api.hefesto.dto.UserDto;
import com.api.hefesto.service.RabbitMqService;
import com.api.hefesto.service.TicketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PriceConsumer {
    private static final Logger LOG = LogManager.getLogger(PriceConsumer.class);
    private ObjectMapper mapper = new ObjectMapper();

    private UserDto userDto = new UserDto();
    private ReportDto reportDto = new ReportDto();

    @Value("${rabbitmq.queue.report.price}")
    private String queueReportPrice;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private RabbitMqService rabbitMqService;

    @RabbitListener(queues = "${rabbitmq.queue.scheduler.price}")
    public void PriceListen(String reportPrice) {
        LOG.info("Price Save of Listener: " + reportPrice);

        if (reportPrice != null) {
            reportPrice = reportPrice.replace("\\\"", "\"").trim();
            if (reportPrice.startsWith("\"") && reportPrice.endsWith("\"")) {
                reportPrice = reportPrice.substring(1, reportPrice.length() - 1);
            }
        }

        try {
            reportDto = mapper.readValue(reportPrice, ReportDto.class);
        } catch (JsonProcessingException e) {
            LOG.error("Error to convert json to ReportDto: " + e.getMessage());
        }

        LOG.debug("ReportDto: " + reportDto.toString());

        List<ListCodeDto> ticketCode = ticketService.getAllTicketCode();

        LOG.info("Ticket Code: " + ticketCode);

        if (ticketCode != null) {
            for (ListCodeDto listCodeDto : ticketCode) {
                rabbitMqService.sendMessage(queueReportPrice, listCodeDto);
            }
        }
    }


}
