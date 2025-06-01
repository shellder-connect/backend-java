package br.com.fiap.backendjava.services.impl;

import br.com.fiap.backendjava.domains.Abrigo;
import br.com.fiap.backendjava.gateways.dtos.abrigo.AbrigoCreateDTO;
import br.com.fiap.backendjava.gateways.dtos.abrigo.AbrigoDetailDTO;
import br.com.fiap.backendjava.gateways.dtos.abrigo.AbrigoUpdateDTO;
import br.com.fiap.backendjava.gateways.repositories.AbrigoRepository;
import br.com.fiap.backendjava.mappers.AbrigoMapper;
import br.com.fiap.backendjava.services.AbrigoService;
import br.com.fiap.backendjava.services.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AbrigoServiceImpl implements AbrigoService {

    private final AbrigoRepository repository;
    private final AbrigoMapper mapper;
    private final ChatClient chatClient;

    @Override
    public Abrigo criar(AbrigoCreateDTO abrigoCreateDTO) {
        return repository.save(mapper.toEntityFromCreateDTO(abrigoCreateDTO));
    }

    @Override
    public Abrigo buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Abrigo não encontrado. ID: " + id));
    }

    @Override
    public List<Abrigo> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public Page<AbrigoDetailDTO> findPage(Pageable pageable, String descricao) {
        Page<Abrigo> page;
        if (descricao != null && !descricao.isBlank()) {
            page = repository.findByDescricaoContainingIgnoreCase(descricao, pageable);
        } else {
            page = repository.findAll(pageable);
        }
        return page.map(mapper::toDetailDTO);
    }

    @Override
    public Abrigo atualizar(Integer id, AbrigoUpdateDTO abrigoUpdateDTO) {
        Abrigo abrigoExistente = buscarPorId(id);
        mapper.toUpdateEntityFromDTO(abrigoUpdateDTO, abrigoExistente);
        return repository.save(abrigoExistente);
    }


    @Override
    public Boolean deletar(Integer id) {
        Abrigo abrigo = buscarPorId(id);
        repository.delete(abrigo);
        return true;
    }

    public String gerarResumoAbrigos(List<Abrigo> abrigos) {
        String promptBase;
        try {
            var resource = new ClassPathResource("prompts/prompt-abrigo.txt");
            byte[] bytes = resource.getInputStream().readAllBytes();
            promptBase = new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o prompt base para abrigos", e);
        }

        StringBuilder prompt = new StringBuilder(promptBase).append("\n");

        for (Abrigo a : abrigos) {
            prompt.append("- Abrigo: ").append(a.getDescricao())
                    .append(" | Capacidade: ").append(a.getCapacidadeTotal())
                    .append(" | Ocupação Atual: ").append(a.getOcupacaoAtual()).append("\n");
        }

        return chatClient.prompt()
                .user(prompt.toString())
                .call()
                .content();
    }

}
