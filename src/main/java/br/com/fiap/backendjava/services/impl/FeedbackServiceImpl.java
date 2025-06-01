package br.com.fiap.backendjava.services.impl;

import br.com.fiap.backendjava.domains.Feedback;
import br.com.fiap.backendjava.gateways.repositories.FeedbackRepository;
import br.com.fiap.backendjava.services.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository repository;
    private final ChatClient chatClient;

    @Override
    public Feedback buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Feedback não encontrado com ID: " + id));
    }

    @Override
    public List<Feedback> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public String gerarResumoFeedbacks(List<Feedback> feedbacks) {
        String promptBase;
        try {
            var resource = new ClassPathResource("prompts/prompt-feedback.txt");
            byte[] bytes = resource.getInputStream().readAllBytes();
            promptBase = new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o prompt base para feedbacks", e);
        }

        StringBuilder prompt = new StringBuilder(promptBase).append("\n");

        for (Feedback feedback : feedbacks) {
            prompt.append("Feedback: ").append(feedback.getComentario())
                    .append(", Avaliação: ").append(feedback.getNota())
                    .append((", Data: ")).append(feedback.getDataFeedback())
                    .append((", Autor: ")).append(feedback.getIdAutor())
                    .append(", Avaliado: ").append(feedback.getIdAvaliado())
                    .append("\n");
        }
        return chatClient.prompt()
                .user(prompt.toString())
                .call()
                .content();
    }
}
