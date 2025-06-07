package br.com.fiap.backendjava.gateways.controllers;

import br.com.fiap.backendjava.domains.RegistroEvento;
import br.com.fiap.backendjava.security.UserDetailsImpl;
import br.com.fiap.backendjava.services.RegistroEventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/registro-eventos")
@RequiredArgsConstructor
public class RegistroEventoController {

    private final RegistroEventoService registroEventoService;

    @GetMapping
    public String getEventosPage(Model model) {
        List<RegistroEvento> eventos = registroEventoService.buscarTodos();

        model.addAttribute("eventos", eventos);

        if (eventos.isEmpty()) {
            model.addAttribute("eventoVazio", true);
        }

        return "evento_page";
    }

    @GetMapping("/resumo-eventos")
    public String gerarResumoEventos(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<RegistroEvento> eventos = registroEventoService.buscarTodos();
        String resumo = registroEventoService.gerarResumoEventos(eventos);

        model.addAttribute("eventos", eventos);
        model.addAttribute("resumoIA", resumo);

        return "evento_page";
    }
}
