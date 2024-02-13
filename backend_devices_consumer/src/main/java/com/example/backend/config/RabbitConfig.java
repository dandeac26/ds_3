package com.example.backend.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    Queue queue(){
        return new Queue("my_queue_1", true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("exchange_1");
    }

    @Bean
    Binding binding (Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("my_queue_1");
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUri("amqps://cow.rmq2.cloudamqp.com/hkbrlyjf");
        connectionFactory.setUsername("hkbrlyjf");
        connectionFactory.setPassword("jB1cXwgWrLPhsSlbfqKosHHWWugMP5bK");
        connectionFactory.setPort(5671);
        connectionFactory.setVirtualHost("hkbrlyjf");
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setConfirmCallback(((correlationData, ack, cause) -> {
            if (!ack) {
                System.out.println("Message send failed: " + cause);
            }
        }));
        return template;
    }
}
