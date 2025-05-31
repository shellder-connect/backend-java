package br.com.fiap.backendjava.domains.dtos.user;

import br.com.fiap.backendjava.domains.Endereco;
import br.com.fiap.backendjava.domains.enums.Role;

import java.time.LocalDate;

public record UserCreateDTO(
        String nome,
        String username,
        String senha,
        Role role,
        String telefone,
        Endereco idEndereco,
        LocalDate dataNascimento,
        String documento,
        Boolean status
) {
}
