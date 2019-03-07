package com.margomicroservices.order.amqp.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderCreatedEvent {
    @JsonProperty
    private final Long time;

    @JsonProperty
    private final OrderStatus status = OrderStatus.CREATED;

    @JsonProperty
    private final Long orderId;

    public OrderCreatedEvent(Long orderId) {
        this.orderId = orderId;

        time = System.currentTimeMillis();
    }
}
