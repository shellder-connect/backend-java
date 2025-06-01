package br.com.fiap.backendjava.gateways.controllers;

import br.com.fiap.backendjava.domains.User;
import br.com.fiap.backendjava.security.UserDetailsImpl;
import br.com.fiap.backendjava.security.UserDetailsServiceImpl;
import br.com.fiap.backendjava.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @GetMapping("/home")
    public String adminHome(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        String username = userDetails.getUsername();
        User user = userService.buscarPorUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        model.addAttribute("nomeUsuario", user.getNome());
        model.addAttribute("usernameUsuario", user.getUsername());
        model.addAttribute("telefoneUsuario", user.getTelefone());
        model.addAttribute("enderecoUsuario", user.getIdEndereco());
        model.addAttribute("dataUsuario", user.getDataNascimento());
        model.addAttribute("documentoUsuario", user.getDocumento());
        model.addAttribute("statusUsuario", user.getStatus() ? "Ativo" : "Inativo");
        return "admin_home";
    }
}
