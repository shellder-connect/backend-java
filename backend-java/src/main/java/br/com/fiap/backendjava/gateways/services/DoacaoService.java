package br.com.fiap.backendjava.gateways.services;

import br.com.fiap.backendjava.domains.Doacao;

import java.util.List;

public interface DoacaoService {
    Doacao criar(Doacao doacao);

    Doacao buscarPorId(Integer id);

    List<Doacao> buscarTodos();

    Doacao atualizar(Integer id, Doacao doacao);

    Boolean deletar(Integer id);
}
