package br.com.fiap.backendjava.gateways.controllers;

import br.com.fiap.backendjava.domains.Feedback;
import br.com.fiap.backendjava.security.UserDetailsImpl;
import br.com.fiap.backendjava.services.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping
    public String getFeedbacksPage(Model model) {
        List<Feedback> feedbacks = feedbackService.buscarTodos();

        model.addAttribute("feedbacks", feedbacks);

        if (feedbacks.isEmpty()) {
            model.addAttribute("feedbackVazio", true);
        }

        return "feedback_page";
    }

    @GetMapping("/resumo-feedbacks")
    public String gerarResumoFeedbacks(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<Feedback> feedbacks = feedbackService.buscarTodos();
        String resumo = feedbackService.gerarResumoFeedbacks(feedbacks);

        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("resumoIA", resumo);

        return "feedback_page";
    }
}
