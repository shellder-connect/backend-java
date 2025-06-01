package br.com.fiap.backendjava.services;

import br.com.fiap.backendjava.domains.Doacao;

import java.util.List;

public interface DoacaoService {

    Doacao buscarPorId(Integer id);

    List<Doacao> buscarTodos();

    String gerarResumoDoacoes(List<Doacao> doacoes);
}
