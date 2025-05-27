package br.com.fiap.backendjava.gateways.services.impl;

import br.com.fiap.backendjava.domains.Categoria;
import br.com.fiap.backendjava.gateways.services.CategoriaService;

import java.util.List;

public class CategoriaServiceImpl implements CategoriaService {

    @Override
    public Categoria criar(Categoria categoria) {
        return null;
    }

    @Override
    public Categoria buscarPorId(Integer id) {
        return null;
    }

    @Override
    public List<Categoria> buscarTodos() {
        return List.of();
    }

    @Override
    public Categoria atualizar(Integer id, Categoria categoria) {
        return null;
    }

    @Override
    public Boolean deletar(Integer id) {
        return null;
    }
}
