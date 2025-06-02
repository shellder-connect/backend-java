package br.com.fiap.backendjava.services.impl;

import br.com.fiap.backendjava.gateways.repositories.UserRepository;
import br.com.fiap.backendjava.services.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final UserRepository repository;
    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(UserRepository repository, JavaMailSender javaMailSender) {
        this.repository = repository;
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String remetente;

    @Override
    public void enviarEmail(String destinatario, String assunto, String msg) {
        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setFrom(remetente);
            email.setTo(destinatario);
            email.setSubject(assunto);
            email.setText(msg);
            javaMailSender.send(email);
            System.out.println("Email enviado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao enviar email: " + e.getMessage());
        }
    }
}
