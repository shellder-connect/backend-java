package br.com.fiap.backendjava.gateways.services;

import br.com.fiap.backendjava.domains.Abrigo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AbrigoService {
    Abrigo criar(Abrigo abrigo);

    Abrigo buscarPorId(Integer id);

    List<Abrigo> buscarTodos();

    Page<Abrigo> buscarTodos(Pageable pageable);

    Abrigo atualizar(Integer id, Abrigo abrigo);

    Boolean deletar(Integer id);
}
