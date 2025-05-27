package br.com.fiap.backendjava.gateways.services.impl;

import br.com.fiap.backendjava.domains.Doacao;
import br.com.fiap.backendjava.domains.Endereco;
import br.com.fiap.backendjava.gateways.services.EnderecoService;

import java.util.List;

public class EnderecoServiceImpl implements EnderecoService {
    @Override
    public Endereco criar(Endereco endereco) {
        return null;
    }

    @Override
    public Doacao buscarPorId(Integer id) {
        return null;
    }

    @Override
    public List<Endereco> buscarTodos() {
        return List.of();
    }

    @Override
    public Endereco atualizar(Integer id, Endereco endereco) {
        return null;
    }

    @Override
    public Boolean deletar(Integer id) {
        return null;
    }
}
