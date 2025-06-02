package br.com.fiap.backendjava.producer;

import br.com.fiap.backendjava.gateways.dtos.user.UserDetailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static br.com.fiap.backendjava.config.UserQueueConfig.USER_EXCHANGE;
import static br.com.fiap.backendjava.config.UserQueueConfig.USER_ROUTING_KEY;

@Component
@RequiredArgsConstructor
public class UserProducer {
    private final RabbitTemplate template;

    public void enviarMensagem(UserDetailDTO response) {
        template.convertAndSend(USER_EXCHANGE, USER_ROUTING_KEY, response);
    }
}
