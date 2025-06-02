package br.com.fiap.backendjava.gateways.controllers;

import br.com.fiap.backendjava.domains.Endereco;
import br.com.fiap.backendjava.gateways.dtos.user.UserCreateDTO;
import br.com.fiap.backendjava.gateways.dtos.user.UserDetailDTO;
import br.com.fiap.backendjava.gateways.dtos.user.UserUpdateDTO;
import br.com.fiap.backendjava.security.UserDetailsImpl;
import br.com.fiap.backendjava.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getUsuariosPage(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  @RequestParam(value = "created", required = false) String created,
                                  @RequestParam(value = "updated", required = false) String updated,
                                  @RequestParam(value = "deleted", required = false) String deleted,
                                  Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<UserDetailDTO> usuariosPage = userService.buscarTodos(pageable);

        model.addAttribute("usuariosPage", usuariosPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", usuariosPage.getTotalPages());

        if (created != null) model.addAttribute("usuarioCreated", true);
        if (updated != null) model.addAttribute("usuarioUpdated", true);
        if (deleted != null) model.addAttribute("usuarioDeleted", true);
        if (usuariosPage.isEmpty()) model.addAttribute("usuarioVazio", true);

        return "usuario_page";
    }


    @GetMapping("/criar")
    public String getCadastroUsuarioPage(Model model) {
        Endereco endereco = new Endereco();
        endereco.setRua("");
        endereco.setNumero("");
        endereco.setBairro("");
        endereco.setCidade("");
        endereco.setEstado("");
        endereco.setCep("");
        endereco.setComplemento("");

        model.addAttribute("novoUsuario", new UserCreateDTO(
                "",
                "",
                "",
                null,
                "",
                null,
                "",
                true,
                endereco
        ));
        return "create_usuario_page";
    }


    @PostMapping("/criar")
    public String criarUsuario(@ModelAttribute("novoUsuario") UserCreateDTO dto, Model model) {
        try {
            userService.criar(dto);
            return "redirect:/usuarios?created=true";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao cadastrar usuário: " + e.getMessage());
            model.addAttribute("novoUsuario", dto);
            return "create_usuario_page";
        }
    }

    @GetMapping("/editar/{id}")
    public String getEditarUsuario(@PathVariable Integer id, Model model) {
        UserDetailDTO usuario = userService.buscarPorId(id);
        UserUpdateDTO dto = new UserUpdateDTO(
                usuario.nome(),
                usuario.telefone(),
                usuario.idEndereco(),
                usuario.dataNascimento(),
                usuario.documento(),
                usuario.status()
        );
        model.addAttribute("usuarioId", id);
        model.addAttribute("usuario", dto);
        return "edit_usuario_page";
    }

    @PostMapping("/editar")
    public String editarUsuario(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                @RequestParam Integer id,
                                @ModelAttribute UserUpdateDTO dto,
                                Model model) {
        try {
            userService.atualizar(id, dto);
            return "redirect:/usuarios?updated=true";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao editar usuário: " + e.getMessage());
            model.addAttribute("usuario", dto);
            return "edit_usuario_page";
        }
    }

    @GetMapping("/deletar/{id}")
    public String deletarUsuario(@PathVariable Integer id) {
        try {
            userService.deletar(id);
            return "redirect:/usuarios?deleted=true";
        } catch (Exception e) {
            return "redirect:/usuarios?deletedError=true";
        }
    }
}

