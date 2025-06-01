package br.com.fiap.backendjava.services.impl;

import br.com.fiap.backendjava.domains.Categoria;
import br.com.fiap.backendjava.gateways.repositories.CategoriaRepository;
import br.com.fiap.backendjava.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository repository;

    @Override
    public Categoria criar(Categoria categoria) {
        return repository.save(categoria);
    }

    @Override
    public Categoria buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com id: " + id));
    }

    @Override
    public List<Categoria> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public Categoria atualizar(Integer id, Categoria categoriaAtualizada) {
        Categoria existente = buscarPorId(id);
        existente.setDescricao(categoriaAtualizada.getDescricao());
        return repository.save(existente);
    }

    @Override
    public Boolean deletar(Integer id) {
        Categoria categoria = buscarPorId(id);
        repository.delete(categoria);
        return true;
    }
}
