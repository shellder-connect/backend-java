package br.com.fiap.backendjava.services.impl;

import br.com.fiap.backendjava.domains.Doacao;
import br.com.fiap.backendjava.gateways.repositories.DoacaoRepository;
import br.com.fiap.backendjava.services.DoacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoacaoServiceImpl implements DoacaoService {

    private final DoacaoRepository repository;
    private final ChatClient chatClient;

    @Override
    public Doacao buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doação não encontrada com id " + id));
    }

    @Override
    public List<Doacao> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public String gerarResumoDoacoes(List<Doacao> doacoes) {
        String promptBase;
        try {
            var resource = new ClassPathResource("prompts/prompt-doacao.txt");
            byte[] bytes = resource.getInputStream().readAllBytes();
            promptBase = new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o prompt base para doacoes", e);
        }

        StringBuilder prompt = new StringBuilder(promptBase).append("\n");

        for (Doacao doacao : doacoes) {
            prompt.append("Abrigo: ").append(doacao.getAbrigo())
                    .append(", Descrição: ").append(doacao.getDescricao())
                    .append(", Categoria: ").append(doacao.getCategoria())
                    .append(", Quantidade: ").append(doacao.getQuantidade())
                    .append("\n");
        }
        return chatClient.prompt()
                .user(prompt.toString())
                .call()
                .content();
    }
}
