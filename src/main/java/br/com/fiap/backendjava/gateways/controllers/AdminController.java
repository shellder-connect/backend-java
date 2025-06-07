package br.com.fiap.backendjava.gateways.controllers;

import br.com.fiap.backendjava.domains.Endereco;
import br.com.fiap.backendjava.domains.User;
import br.com.fiap.backendjava.gateways.dtos.user.UserUpdateDTO;
import br.com.fiap.backendjava.security.UserDetailsImpl;
import br.com.fiap.backendjava.services.UserService;
import br.com.fiap.backendjava.utils.FormatUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/home")
    public String adminHome(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        String username = userDetails.getUsername();
        User user = userService.buscarPorUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        model.addAttribute("nomeUsuario", user.getNome());
        model.addAttribute("usernameUsuario", user.getUsername());
        model.addAttribute("telefoneUsuario", user.getTelefone());
        String enderecoFormatado = user.getIdEndereco() == null ? "Não informado"
                : String.format("%s, %s - %s, %s/%s - CEP: %s%s",
                user.getIdEndereco().getRua(),
                user.getIdEndereco().getNumero(),
                user.getIdEndereco().getBairro(),
                user.getIdEndereco().getCidade(),
                user.getIdEndereco().getEstado(),
                user.getIdEndereco().getCep(),
                user.getIdEndereco().getComplemento() != null ? " - " + user.getIdEndereco().getComplemento() : ""
        );
        model.addAttribute("enderecoUsuario", enderecoFormatado);
        model.addAttribute("dataUsuario",  FormatUtil.getLocalDateFormatted(user.getDataNascimento()));
        model.addAttribute("documentoUsuario", user.getDocumento());
        model.addAttribute("statusUsuario", user.getStatus() ? "Ativo" : "Inativo");
        return "admin_home";
    }

    @GetMapping("/editar")
    public String editarAdmin(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        String username = userDetails.getUsername();
        User user = userService.buscarPorUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado para o e-mail: " + username));

        if (user.getIdEndereco() == null) {
            user.setIdEndereco(new Endereco());
        }

        model.addAttribute("user", user);
        return "edit_admin_page";
    }

    @PostMapping("/editar")
    public String atualizarAdmin(@AuthenticationPrincipal UserDetailsImpl userDetails, User userForm) {
        String username = userDetails.getUsername();
        User userLogado = userService.buscarPorUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado para o e-mail: " + username));

        UserUpdateDTO userUpdateDTO = new UserUpdateDTO(
                userForm.getNome(),
                userForm.getTelefone(),
                userForm.getIdEndereco(),
                userForm.getDataNascimento(),
                userForm.getDocumento(),
                userForm.getStatus()
        );

        userService.atualizar(userLogado.getId(), userUpdateDTO);
        return "redirect:/admin/home?updated=true";
    }

}
