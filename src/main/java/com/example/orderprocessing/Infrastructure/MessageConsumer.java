package com.example.orderprocessing.Infrastructure;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @RabbitListener(queues = "orders.queue")
    public void receiveMessage(String message) {
        // Procesar el mensaje
        System.out.println("Received Message: " + message);
    }
}
