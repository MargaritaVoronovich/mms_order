package com.margomicroservices.order.amqp.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.margomicroservices.order.model.Order;


public class OrderCreatedEvent {
    private ObjectMapper objectMapper;

    @JsonProperty
    private final Long time;

    private final Order order;

    public OrderCreatedEvent(Order order, ObjectMapper objectMapper) {
        this.order = order;
        this.objectMapper = objectMapper;

        time = System.currentTimeMillis();
    }

    public String toJsonString() throws JsonProcessingException {
        return objectMapper.writeValueAsString(this);
    }

    public Long getTime() {
        return time;
    }

    @JsonSerialize(as = Order.class)
    public Order getOrder() {
        return order;
    }
}
