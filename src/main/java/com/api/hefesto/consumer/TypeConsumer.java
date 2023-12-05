package com.api.hefesto.consumer;

import com.api.hefesto.model.TypeModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class TypeConsumer {
    private static final Logger LOG = LogManager.getLogger(TypeConsumer.class);

    private final ObjectMapper mapper = new ObjectMapper();

    @RabbitListener(queues = "${rabbitmq.queue.type.hefesto}")
    public void TypeListen(@Payload String typeMq) throws JsonProcessingException {
        LOG.info("Type Save of Listener: " + typeMq);
        TypeModel type = mapper.readValue(typeMq, TypeModel.class);
        LOG.debug("Type Save of Listener: " + type.toString());
    }
}
