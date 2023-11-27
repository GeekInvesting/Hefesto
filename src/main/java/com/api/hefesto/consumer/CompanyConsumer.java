package com.api.hefesto.consumer;

import com.api.hefesto.model.CompanyModel;
import com.api.hefesto.service.CompanyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CompanyConsumer {
    private static final Logger LOG = LogManager.getLogger(CompanyConsumer.class);

    @Autowired
    private CompanyService companyService;

    private CompanyModel company = new CompanyModel();
    private ObjectMapper mapper = new ObjectMapper();

    @RabbitListener(queues = "${rabbitmq.queue.company.hefesto}")
    public void CompanyListen(@Payload String companyMq) throws JsonProcessingException {
        LOG.info("Company Save of Listener: " + companyMq);

        company = mapper.readValue(companyMq, CompanyModel.class);

        companyService.saveCompanyByConsumer(company);
    }
}
