package com.margomicroservices.order.amqp.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class OrderCreatedEvent {
    private ObjectMapper objectMapper;

    @JsonProperty
    private final Long time;

    @JsonProperty
    private final String status = "created";

    @JsonProperty
    private final Long orderId;

    public OrderCreatedEvent(Long orderId, ObjectMapper objectMapper) {
        this.orderId = orderId;
        this.objectMapper = objectMapper;

        time = System.currentTimeMillis();
    }

    public String toJsonString() throws JsonProcessingException {
        return objectMapper.writeValueAsString(this);
    }

    public Long getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public Long getOrderId() {
        return orderId;
    }
}
