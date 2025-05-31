package br.com.fiap.backendjava.services.impl;

import br.com.fiap.backendjava.domains.RegistroEvento;
import br.com.fiap.backendjava.services.RegistroEventoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class RegistroEventoServiceImpl implements RegistroEventoService {
    @Override
    public RegistroEvento criar(RegistroEvento registroEvento) {
        return null;
    }

    @Override
    public RegistroEvento buscarPorId(Integer id) {
        return null;
    }

    @Override
    public List<RegistroEvento> buscarTodos() {
        return List.of();
    }

    @Override
    public RegistroEvento atualizar(Integer id, RegistroEvento registroEvento) {
        return null;
    }

    @Override
    public Boolean deletar(Integer id) {
        return null;
    }
}
