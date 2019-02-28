package com.margomicroservices.order.controller;

import com.margomicroservices.order.model.Order;
import com.margomicroservices.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    OrderController(
            final OrderService orderService
    ) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> all() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<?> one(@PathVariable final Long id) {
        final Optional<Order> order = orderService.findById(id);

        if (!order.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(order.get());
    }

    @PostMapping("/orders")
    public ResponseEntity<?> create(@Valid @RequestBody final Order order) throws Exception {
        return ResponseEntity.ok(orderService.create(order));
    }

    @DeleteMapping("orders/{id}")
    ResponseEntity<?> delete(@PathVariable final Long id) {
        final Optional<Order> order = orderService.findById(id);

        if (!order.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        orderService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
