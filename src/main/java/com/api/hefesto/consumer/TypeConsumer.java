package com.api.hefesto.consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class TypeConsumer {
    private static final Logger LOG = LogManager.getLogger(TypeConsumer.class);

    @RabbitListener(queues = "${rabbitmq.queue.type.hefesto}")
    public void TypeListen(@Payload String typeMq) {
        LOG.info("Type Save of Listener: " + typeMq);
    }
}
