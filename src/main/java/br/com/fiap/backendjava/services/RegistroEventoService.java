package br.com.fiap.backendjava.services;

import br.com.fiap.backendjava.domains.Doacao;
import br.com.fiap.backendjava.domains.RegistroEvento;

import java.util.List;

public interface RegistroEventoService {
    RegistroEvento buscarPorId(Integer id);

    List<RegistroEvento> buscarTodos();
}
