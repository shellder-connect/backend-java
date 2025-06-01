package br.com.fiap.backendjava.gateways.controllers;

import br.com.fiap.backendjava.domains.Categoria;
import br.com.fiap.backendjava.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public String getCategoriasPage(@RequestParam(value = "created", required = false) String created,
                                    @RequestParam(value = "updated", required = false) String updated,
                                    @RequestParam(value = "deleted", required = false) String deleted,
                                    Model model) {

        List<Categoria> categorias = categoriaService.buscarTodos();
        model.addAttribute("categorias", categorias);

        if (created != null) model.addAttribute("categoriaCreated", true);
        if (updated != null) model.addAttribute("categoriaUpdated", true);
        if (deleted != null) model.addAttribute("categoriaDeleted", true);
        if (categorias.isEmpty()) model.addAttribute("categoriaVazia", true);

        return "categoria_page";
    }

    @GetMapping("/criar")
    public String getCadastroCategoriaPage(Model model) {
        model.addAttribute("novaCategoria", new Categoria());
        return "create_categoria_page";
    }

    @PostMapping("/criar")
    public String criarCategoria(@ModelAttribute Categoria categoria, Model model) {
        try {
            categoriaService.criar(categoria);
            return "redirect:/categorias?created=true";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao cadastrar categoria: " + e.getMessage());
            return "create_categoria_page";
        }
    }

    @GetMapping("/editar/{id}")
    public String getEditarCategoria(@PathVariable Integer id, Model model) {
        Categoria categoria = categoriaService.buscarPorId(id);
        model.addAttribute("categoriaId", id);
        model.addAttribute("categoria", categoria);
        return "edit_categoria_page";
    }

    @PostMapping("/editar")
    public String editarCategoria(@RequestParam Integer id,
                                  @ModelAttribute Categoria categoria,
                                  Model model) {
        try {
            categoriaService.atualizar(id, categoria);
            return "redirect:/categorias?updated=true";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao editar categoria: " + e.getMessage());
            return "edit_categoria_page";
        }
    }

    @GetMapping("/deletar/{id}")
    public String deletarCategoria(@PathVariable Integer id) {
        try {
            categoriaService.deletar(id);
            return "redirect:/categorias?deleted=true";
        } catch (Exception e) {
            return "redirect:/categorias?deletedError=true";
        }
    }
}
