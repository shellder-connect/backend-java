package br.com.fiap.backendjava.gateways.services;

import br.com.fiap.backendjava.domains.Doacao;
import br.com.fiap.backendjava.domains.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback criar(Feedback feedback);

    Feedback buscarPorId(Integer id);

    List<Feedback> buscarTodos();

    Feedback atualizar(Integer id, Feedback feedback);

    Boolean deletar(Integer id);
}
