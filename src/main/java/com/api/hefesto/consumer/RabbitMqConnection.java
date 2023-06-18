package com.api.hefesto.consumer;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;


@Component
public class RabbitMqConnection {
    private static final String NAME_EXCHANGE = "amq.direct";
    private AmqpAdmin amqpAdmin;

    @Value("${rabbitmq.queue.ticket.zeus}")
    private String queueTicketZeus;

    public RabbitMqConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange exchange() {
        return new DirectExchange(NAME_EXCHANGE, true, false);
    }

    private Binding binding(Queue queue, DirectExchange exchange) {
        return new Binding(queue.getName(),
                Binding.DestinationType.QUEUE,
                exchange.getName(),
                queue.getName(),
                null);
    }

    @PostConstruct
    private void addQueue() {
        Queue queue = this.queue(queueTicketZeus);
        DirectExchange exchange = this.exchange();
        Binding binding = this.binding(queue, exchange);

        this.amqpAdmin.declareQueue(queue);
        this.amqpAdmin.declareExchange(exchange);
        this.amqpAdmin.declareBinding(binding);
    }
}
