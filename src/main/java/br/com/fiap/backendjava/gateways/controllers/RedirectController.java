package br.com.fiap.backendjava.gateways.controllers;

import br.com.fiap.backendjava.domains.enums.Role;
import br.com.fiap.backendjava.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {
    @GetMapping("/redirect")
    public String redirectByRole(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails.hasRole(Role.ADMIN)) {
            return "redirect:/admin/home";
        } else if (userDetails.hasRole(Role.VOLUNTARIO)) {
            return "redirect:/voluntario/home";
        } else if (userDetails.hasRole(Role.EMPRESAPARCEIRA)) {
            return "redirect:/empresa/home";
        } else if (userDetails.hasRole(Role.SOLICITANTE)) {
            return "redirect:/solicitante/home";
        } else if (userDetails.hasRole(Role.PROFISSIONALSAUDE)) {
            return "redirect:/saude/home";
        }
        return "redirect:/access-denied";
    }
}
