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

    @Value("${rabbitmq.queue.type.zeus}")
    private String queueTypeZeus;

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
        Queue queueTicket = this.queue(queueTicketZeus);
        Queue queueType = this.queue(queueTypeZeus);

        DirectExchange exchange = this.exchange();

        Binding bindingTicket = this.binding(queueTicket, exchange);
        Binding bindingType = this.binding(queueType, exchange);

        this.amqpAdmin.declareQueue(queueTicket);
        this.amqpAdmin.declareQueue(queueType);

        this.amqpAdmin.declareExchange(exchange);

        this.amqpAdmin.declareBinding(bindingTicket);
        this.amqpAdmin.declareBinding(bindingType);
    }
}
