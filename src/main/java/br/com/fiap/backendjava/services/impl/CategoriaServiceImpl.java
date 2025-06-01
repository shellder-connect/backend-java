package br.com.fiap.backendjava.services.impl;

import br.com.fiap.backendjava.domains.Categoria;
import br.com.fiap.backendjava.services.CategoriaService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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
