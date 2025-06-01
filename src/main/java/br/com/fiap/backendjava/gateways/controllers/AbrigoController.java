package br.com.fiap.backendjava.gateways.controllers;

import br.com.fiap.backendjava.domains.Abrigo;
import br.com.fiap.backendjava.gateways.dtos.abrigo.AbrigoCreateDTO;
import br.com.fiap.backendjava.gateways.dtos.abrigo.AbrigoUpdateDTO;
import br.com.fiap.backendjava.security.UserDetailsImpl;
import br.com.fiap.backendjava.services.AbrigoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/abrigos")
@RequiredArgsConstructor
public class AbrigoController {

    private final AbrigoService abrigoService;

    @GetMapping
    public String getAbrigosPage(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                 @RequestParam(value = "created", required = false) String created,
                                 @RequestParam(value = "updated", required = false) String updated,
                                 @RequestParam(value = "deleted", required = false) String deleted,
                                 Model model) {

        List<Abrigo> abrigos = abrigoService.buscarTodos();
        model.addAttribute("abrigos", abrigos);

        if (created != null) model.addAttribute("abrigoCreated", true);
        if (updated != null) model.addAttribute("abrigoUpdated", true);
        if (deleted != null) model.addAttribute("abrigoDeleted", true);
        if (abrigos.isEmpty()) model.addAttribute("abrigoVazio", true);

        return "abrigo_page";
    }

    @GetMapping("/criar")
    public String getCadastroAbrigoPage(Model model) {
        model.addAttribute("novoAbrigo", new AbrigoCreateDTO(null, null, ""));
        return "create_abrigo_page";
    }

    @PostMapping("/criar")
    public String criarAbrigo(@ModelAttribute AbrigoCreateDTO dto, Model model) {
        try {
            abrigoService.criar(dto);
            return "redirect:/abrigos?created=true";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao cadastrar abrigo: " + e.getMessage());
            return "create_abrigo_page";
        }
    }

    @GetMapping("/editar/{id}")
    public String getEditarAbrigo(@PathVariable Integer id, Model model) {
        Abrigo abrigo = abrigoService.buscarPorId(id);
        AbrigoUpdateDTO dto = new AbrigoUpdateDTO(
                abrigo.getCapacidadeTotal(),
                abrigo.getOcupacaoAtual(),
                abrigo.getDescricao()
        );
        model.addAttribute("abrigoId", id);
        model.addAttribute("abrigo", dto);
        return "edit_abrigo_page";
    }

    @PostMapping("/editar")
    public String editarAbrigo(@AuthenticationPrincipal UserDetailsImpl userDetails,
                               @RequestParam Integer id,
                               @ModelAttribute AbrigoUpdateDTO dto,
                               Model model) {
        try {
            abrigoService.atualizar(id, dto);
            return "redirect:/abrigos?updated=true";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao editar abrigo: " + e.getMessage());
            return "edit_abrigo_page";
        }
    }

    @GetMapping("/deletar/{id}")
    public String deletarAbrigo(@PathVariable Integer id) {
        try {
            abrigoService.deletar(id);
            return "redirect:/abrigos?deleted=true";
        } catch (Exception e) {
            return "redirect:/abrigos?deletedError=true";
        }
    }

    @GetMapping("/resumo-abrigos")
    public String gerarResumoAbrigos(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<Abrigo> abrigos = abrigoService.buscarTodos();
        String resumo = abrigoService.gerarResumoAbrigos(abrigos);
        model.addAttribute("abrigos", abrigos);
        model.addAttribute("resumoIA", resumo);
        return "abrigo_page";
    }

}
