package com.api.hefesto.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RabbitMqService {
    private static Logger LOG = LogManager.getLogger(RabbitMqService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(String queue, Object message) {
        try {
            LOG.info("Sending message to queue: " + queue);
            rabbitTemplate.convertAndSend(queue, objectMapper.writeValueAsString(message));
        } catch (Exception e) {
            LOG.error("Error sending message to queue: " + queue);
            LOG.error(e.getMessage());
        }
    }
}
