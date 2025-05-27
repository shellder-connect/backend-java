package br.com.fiap.backendjava.gateways.services;

import br.com.fiap.backendjava.domains.Doacao;
import br.com.fiap.backendjava.domains.RegistroEvento;

import java.util.List;

public interface RegistroEventoService {
    RegistroEvento criar(RegistroEvento registroEvento);

    RegistroEvento buscarPorId(Integer id);

    List<RegistroEvento> buscarTodos();

    RegistroEvento atualizar(Integer id, RegistroEvento registroEvento);

    Boolean deletar(Integer id);
}
