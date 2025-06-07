package br.com.fiap.backendjava.services;

import br.com.fiap.backendjava.domains.Categoria;

import java.util.List;

public interface CategoriaService {
    Categoria criar(Categoria categoria);

    Categoria buscarPorId(Integer id);

    List<Categoria> buscarTodos();

    Categoria atualizar(Integer id, Categoria categoria);

    Boolean deletar(Integer id);
}
