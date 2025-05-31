package br.com.fiap.backendjava.domains.dtos.user;

import br.com.fiap.backendjava.domains.Endereco;
import br.com.fiap.backendjava.domains.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserCreateDTO(
        String nome,
        String username,
        @NotBlank @Size(min = 6, max = 100) @Pattern(regexp = "(?=\\S+$).+")
        String senha,
        Role role,
        String telefone,
        Endereco idEndereco, // pode ser null
        LocalDate dataNascimento,
        String documento,
        Boolean status
) {}

