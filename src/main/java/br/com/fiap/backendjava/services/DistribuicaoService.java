package br.com.fiap.backendjava.services;

import br.com.fiap.backendjava.domains.Distribuicao;
import br.com.fiap.backendjava.domains.Doacao;

import java.util.List;

public interface DistribuicaoService {

    Distribuicao buscarPorId(Integer id);

    List<Distribuicao> buscarTodos();

    Distribuicao atualizar(Integer id, Distribuicao distribuicao);

    Boolean deletar(Integer id);
}
