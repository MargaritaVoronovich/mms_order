package com.margomicroservices.order.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String HISTORY_QUEUE = "history_queue";
    public static final String DELIVERY_QUEUE = "delivery_queue";

    @Value("${topic.exchange}")
    private String topicExchange;
    @Value("${queue.name}")
    private String queueName;
    @Value("${routing.key}")
    private String routingKey;

    @Bean
    Queue historyQueue() {
        return new Queue(HISTORY_QUEUE, true);
    }

    @Bean
    Queue deliveryQueue() {
        return new Queue(DELIVERY_QUEUE, true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchange);
    }

    @Bean
    Binding historyBinding(TopicExchange exchange) {
        return BindingBuilder.bind(historyQueue()).to(exchange).with(routingKey);
    }

    @Bean
    Binding deliveryBinding(TopicExchange exchange) {
        return BindingBuilder.bind(deliveryQueue()).to(exchange).with(routingKey);
    }
}
