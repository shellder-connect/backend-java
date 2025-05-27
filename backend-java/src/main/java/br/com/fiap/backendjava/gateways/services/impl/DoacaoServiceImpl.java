package br.com.fiap.backendjava.gateways.services.impl;

import br.com.fiap.backendjava.domains.Doacao;
import br.com.fiap.backendjava.gateways.services.DoacaoService;

import java.util.List;

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
