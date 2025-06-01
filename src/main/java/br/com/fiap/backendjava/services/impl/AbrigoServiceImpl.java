package br.com.fiap.backendjava.services.impl;

import br.com.fiap.backendjava.domains.Abrigo;
import br.com.fiap.backendjava.services.AbrigoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AbrigoServiceImpl implements AbrigoService {
    @Override
    public Abrigo criar(Abrigo abrigo) {
        return null;
    }

    @Override
    public Abrigo buscarPorId(Integer id) {
        return null;
    }

    @Override
    public List<Abrigo> buscarTodos() {
        return List.of();
    }

    @Override
    public Page<Abrigo> buscarTodos(Pageable pageable) {
        return null;
    }

    @Override
    public Abrigo atualizar(Integer id, Abrigo abrigo) {
        return null;
    }

    @Override
    public Boolean deletar(Integer id) {
        return null;
    }
}
