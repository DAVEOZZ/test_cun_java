package com.example.orderprocessing.Presentation;

import com.example.orderprocessing.Application.OrderService;
import com.example.orderprocessing.Domain.Order;
import com.example.orderprocessing.Infrastructure.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final MessageProducer messageProducer;

    @Autowired
    public OrderController(OrderService orderService, MessageProducer messageProducer) {
        this.orderService = orderService;
        this.messageProducer = messageProducer;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        messageProducer.sendMessage("Order Created: " + createdOrder.getId());
        return createdOrder;
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }
}
