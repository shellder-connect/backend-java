package br.com.fiap.backendjava.gateways.controllers;

import br.com.fiap.backendjava.domains.Doacao;
import br.com.fiap.backendjava.security.UserDetailsImpl;
import br.com.fiap.backendjava.services.DoacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doacoes")
@RequiredArgsConstructor
public class DoacaoController {

    private final DoacaoService doacaoService;

    @GetMapping
    public String getDoacoesPage(Model model) {
        List<Doacao> doacoes = doacaoService.buscarTodos();

        model.addAttribute("doacoes", doacoes);

        if (doacoes.isEmpty()) {
            model.addAttribute("doacaoVazia", true);
        }

        return "doacao_page";
    }

    @GetMapping("/resumo-doacoes")
    public String gerarResumoDoacoes(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<Doacao> doacoes = doacaoService.buscarTodos();
        String resumo = doacaoService.gerarResumoDoacoes(doacoes);
        model.addAttribute("doacoes", doacoes);
        model.addAttribute("resumoIA", resumo);
        return "doacao_page";
    }
}
