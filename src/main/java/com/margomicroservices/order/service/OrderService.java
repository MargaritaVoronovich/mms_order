package com.margomicroservices.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.margomicroservices.order.amqp.QueueProducer;
import com.margomicroservices.order.amqp.event.OrderCreatedEvent;
import com.margomicroservices.order.model.Order;
import com.margomicroservices.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final QueueProducer queueProducer;
    private final ObjectMapper objectMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, QueueProducer queueProducer, ObjectMapper objectMapper) {
        this.orderRepository = orderRepository;
        this.queueProducer = queueProducer;
        this.objectMapper = objectMapper;
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    public Order create(Order order) throws JsonProcessingException {
        Order savedOrder = orderRepository.save(order);

        queueProducer.produce(new OrderCreatedEvent(savedOrder.getId(), objectMapper).toJsonString());

        return savedOrder;
    }
}
