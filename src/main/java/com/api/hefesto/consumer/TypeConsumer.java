package com.api.hefesto.consumer;

import com.api.hefesto.model.TypeModel;
import com.api.hefesto.service.TypeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class TypeConsumer {
    private static final Logger LOG = LogManager.getLogger(TypeConsumer.class);

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private TypeService typeService = new TypeService();

    @RabbitListener(queues = "${rabbitmq.queue.type.hefesto}")
    public void TypeListen(@Payload String typeMq) throws JsonProcessingException {
        LOG.info("Type Save of Listener: " + typeMq);
        TypeModel type = mapper.readValue(typeMq, TypeModel.class);
        if((type.getTypeCode() == null) || (type.getTypeName() == null)){
            LOG.error("Data Type is null " + typeMq);
            return;
        }
        TypeModel typeSaved = typeService.saveTypeByConsumer(type);
        LOG.info("Type Saved: " + typeSaved.toString());
    }
}
