package br.com.fiap.backendjava.services.impl;

import br.com.fiap.backendjava.domains.RegistroEvento;
import br.com.fiap.backendjava.gateways.repositories.RegistroEventoRespository;
import br.com.fiap.backendjava.services.RegistroEventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistroEventoServiceImpl implements RegistroEventoService {

    private final RegistroEventoRespository repository;
    private final ChatClient chatClient;

    @Override
    public RegistroEvento buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Registro de evento não encontrado com o ID: " + id));
    }

    @Override
    public List<RegistroEvento> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public String gerarResumoEventos(List<RegistroEvento> eventos) {
        String promptBase;
        try {
            var resource = new ClassPathResource("prompts/prompt-registro-evento.txt");
            byte[] bytes = resource.getInputStream().readAllBytes();
            promptBase = new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o prompt base para feedbacks", e);
        }
        StringBuilder prompt = new StringBuilder(promptBase).append("\n");
        for (RegistroEvento evento : eventos) {
            prompt.append("Evento: ").append(evento.getDescricao())
                    .append(", Data: ").append(evento.getDataHora())
                    .append(", Usuário: ").append(evento.getIdUsuario())
                    .append(", Localização: ").append(evento.getLocalizacao())
                    .append(", Abrigo: ").append(evento.getIdAbrigo())
                    .append("\n");
        }
        return chatClient.prompt()
                .user(prompt.toString())
                .call()
                .content();
    }
}
