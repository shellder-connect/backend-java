package br.com.fiap.backendjava.services.impl;

import br.com.fiap.backendjava.domains.RegistroEvento;
import br.com.fiap.backendjava.services.RegistroEventoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistroEventoServiceImpl implements RegistroEventoService {
    @Override
    public RegistroEvento buscarPorId(Integer id) {
        return null;
    }

    @Override
    public List<RegistroEvento> buscarTodos() {
        return List.of();
    }
}
