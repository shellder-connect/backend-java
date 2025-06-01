package br.com.fiap.backendjava.gateways.controllers;

import br.com.fiap.backendjava.domains.Distribuicao;
import br.com.fiap.backendjava.services.DistribuicaoService;
import br.com.fiap.backendjava.utils.FormatUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/distribuicoes")
@RequiredArgsConstructor
public class DistribuicaoController {

    private final DistribuicaoService distribuicaoService;

    @GetMapping
    public String getDistribuicoesPage(@RequestParam(value = "updated", required = false) String updated,
                                       @RequestParam(value = "deleted", required = false) String deleted,
                                       Model model) {

        List<Distribuicao> distribuicoes = distribuicaoService.buscarTodos();

        List<String> datasFormatadas = distribuicoes.stream()
                .map(d -> FormatUtil.getLocalDateFormatted(d.getDataDistribuicao()))
                .collect(Collectors.toList());

        model.addAttribute("distribuicoes", distribuicoes);
        model.addAttribute("datasFormatadas", datasFormatadas);

        if (updated != null) model.addAttribute("distribuicaoUpdated", true);
        if (deleted != null) model.addAttribute("distribuicaoDeleted", true);
        if (distribuicoes.isEmpty()) model.addAttribute("distribuicaoVazia", true);

        return "distribuicao_page";
    }
}
