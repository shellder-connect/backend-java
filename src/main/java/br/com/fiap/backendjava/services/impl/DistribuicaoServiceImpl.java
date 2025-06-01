package br.com.fiap.backendjava.services.impl;

import br.com.fiap.backendjava.domains.Distribuicao;
import br.com.fiap.backendjava.gateways.repositories.DistribuicaoRepository;
import br.com.fiap.backendjava.services.DistribuicaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistribuicaoServiceImpl implements DistribuicaoService {

    private final DistribuicaoRepository repository;

    @Override
    public Distribuicao buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Distribuição não encontrada com ID: " + id));
    }

    @Override
    public List<Distribuicao> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public Distribuicao atualizar(Integer id, Distribuicao novaDistribuicao) {
        Distribuicao existente = buscarPorId(id);

        existente.setQtdDestinada(novaDistribuicao.getQtdDestinada());
        existente.setDataDistribuicao(novaDistribuicao.getDataDistribuicao());
        existente.setUser(novaDistribuicao.getUser());
        existente.setDoacao(novaDistribuicao.getDoacao());

        return repository.save(existente);
    }

    @Override
    public Boolean deletar(Integer id) {
        Distribuicao existente = buscarPorId(id);
        repository.delete(existente);
        return true;
    }
}
