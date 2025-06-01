package br.com.fiap.backendjava.services;

import br.com.fiap.backendjava.domains.Doacao;
import br.com.fiap.backendjava.domains.Feedback;

import java.util.List;

public interface FeedbackService {

    Feedback buscarPorId(Integer id);

    List<Feedback> buscarTodos();

    String gerarResumoFeedbacks(List<Feedback> feedbacks);
}
