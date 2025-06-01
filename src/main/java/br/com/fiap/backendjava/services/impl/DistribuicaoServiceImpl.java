package br.com.fiap.backendjava.services.impl;

import br.com.fiap.backendjava.domains.Distribuicao;
import br.com.fiap.backendjava.domains.Doacao;
import br.com.fiap.backendjava.services.DistribuicaoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistribuicaoServiceImpl implements DistribuicaoService {
    @Override
    public Distribuicao criar(Doacao distribuicao) {
        return null;
    }

    @Override
    public Distribuicao buscarPorId(Integer id) {
        return null;
    }

    @Override
    public List<Distribuicao> buscarTodos() {
        return List.of();
    }

    @Override
    public Distribuicao atualizar(Integer id, Distribuicao distribuicao) {
        return null;
    }

    @Override
    public Boolean deletar(Integer id) {
        return null;
    }
}
