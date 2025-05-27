package br.com.fiap.backendjava.services;

import br.com.fiap.backendjava.domains.Doacao;
import br.com.fiap.backendjava.domains.Endereco;

import java.util.List;

public interface EnderecoService {
    Endereco criar(Endereco endereco);

    Doacao buscarPorId(Integer id);

    List<Endereco> buscarTodos();

    Endereco atualizar(Integer id, Endereco endereco);

    Boolean deletar(Integer id);
}
