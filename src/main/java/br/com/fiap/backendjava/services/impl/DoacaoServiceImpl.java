package br.com.fiap.backendjava.services.impl;

import br.com.fiap.backendjava.domains.Doacao;
import br.com.fiap.backendjava.services.DoacaoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoacaoServiceImpl implements DoacaoService {

    @Override
    public Doacao criar(Doacao doacao) {
        return null;
    }

    @Override
    public Doacao buscarPorId(Integer id) {
        return null;
    }

    @Override
    public List<Doacao> buscarTodos() {
        return List.of();
    }

    @Override
    public Doacao atualizar(Integer id, Doacao doacao) {
        return null;
    }

    @Override
    public Boolean deletar(Integer id) {
        return null;
    }
}
