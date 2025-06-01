package br.com.fiap.backendjava.services.impl;

import br.com.fiap.backendjava.domains.Feedback;
import br.com.fiap.backendjava.services.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    @Override
    public Feedback buscarPorId(Integer id) {
        return null;
    }

    @Override
    public List<Feedback> buscarTodos() {
        return List.of();
    }

}
