package com.api.hefesto.consumer;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.api.hefesto.model.CountryModel;
import com.api.hefesto.service.CountryService;
import com.lib.mq.CountryMq;
import com.lib.queue.CountryQueue;

@Component
public class CountryConsumer {
    private static final Logger LOG = LogManager.getLogger(CountryConsumer.class);

    @Autowired
    private CountryService countryService;

    private CountryModel country = new CountryModel();

    @RabbitListener(queues = CountryQueue.QUEUE_COUNTRY_HEFESTO)
    public void CountryListen(@Payload CountryMq countryMq){
        LOG.info("Country Save of Listener: " + countryMq.toString());

        BeanUtils.copyProperties(countryMq, country);

        countryService.saveCountry(country);
    }
}
