package br.com.fiap.backendjava.services.impl;

import br.com.fiap.backendjava.domains.Feedback;
import br.com.fiap.backendjava.services.FeedbackService;

import java.util.List;

public class FeedbackServiceImpl implements FeedbackService {
    @Override
    public Feedback criar(Feedback feedback) {
        return null;
    }

    @Override
    public Feedback buscarPorId(Integer id) {
        return null;
    }

    @Override
    public List<Feedback> buscarTodos() {
        return List.of();
    }

    @Override
    public Feedback atualizar(Integer id, Feedback feedback) {
        return null;
    }

    @Override
    public Boolean deletar(Integer id) {
        return null;
    }
}
