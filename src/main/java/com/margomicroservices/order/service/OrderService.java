package com.margomicroservices.order.service;

import com.margomicroservices.order.amqp.QueueProducer;
import com.margomicroservices.order.model.Order;
import com.margomicroservices.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    private QueueProducer queueProducer;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    public Order create(Order order) {
        queueProducer.produce("HEY YOU!");

        return orderRepository.save(order);
    }
}
