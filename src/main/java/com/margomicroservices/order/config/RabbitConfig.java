package com.margomicroservices.order.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Value("${fanout.exchange}")
    private String fanoutExchange;

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange(fanoutExchange);
    }
}
