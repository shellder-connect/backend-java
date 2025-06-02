package br.com.fiap.backendjava.consumer;

import br.com.fiap.backendjava.gateways.dtos.user.UserDetailDTO;
import br.com.fiap.backendjava.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static br.com.fiap.backendjava.config.UserQueueConfig.USER_QUEUE;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserConsumer {

    private final EmailService emailService;

    @RabbitListener(queues = USER_QUEUE)
    public void listener(UserDetailDTO response) {
        log.info("User received: {}", response);
        emailService.enviarEmail(response.username(), "Cadastro confirmado",
                "Olá " + response.nome() + ", seu cadastro foi realizado com sucesso!");
    }
}
