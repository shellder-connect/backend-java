package br.com.fiap.backendjava.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserQueueConfig {
    public static final String USER_QUEUE = "userQueue";
    public static final String USER_EXCHANGE = "userExchange";
    public static final String USER_ROUTING_KEY = "userRoutingKey";

    @Bean
    public Queue userQueue() {
        return QueueBuilder.durable(USER_QUEUE).build();
    }

    @Bean
    public Exchange userExchange() {
        return ExchangeBuilder.directExchange(USER_EXCHANGE)
                .durable(true)
                .build();
    }

    @Bean
    public Binding userBinding(Queue userQueue, Exchange userExchange) {
        return BindingBuilder.bind(userQueue)
                .to(userExchange)
                .with(USER_ROUTING_KEY)
                .noargs();
    }
}
