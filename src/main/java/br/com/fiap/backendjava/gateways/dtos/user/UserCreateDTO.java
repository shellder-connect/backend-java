package br.com.fiap.backendjava.gateways.dtos.user;

import br.com.fiap.backendjava.domains.Endereco;
import br.com.fiap.backendjava.domains.enums.Role;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserCreateDTO(

        @NotBlank(message = "O nome é obrigatório.")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
        String nome,

        @NotBlank(message = "O e-mail (username) é obrigatório.")
        @Email(message = "Informe um e-mail válido.")
        String username,

        @NotBlank(message = "A senha é obrigatória.")
        @Size(min = 6, max = 100, message = "A senha deve ter entre 6 e 100 caracteres.")
        @Pattern(regexp = "(?=\\S+$).+", message = "A senha não pode conter espaços.")
        String senha,

        @NotNull(message = "O papel (role) é obrigatório.")
        Role role,

        @Pattern(regexp = "\\(?\\d{2}\\)?\\s?9?\\d{4}-\\d{4}", message = "Telefone inválido.")
        String telefone,

        @Past(message = "A data de nascimento deve ser no passado.")
        LocalDate dataNascimento,

        @Size(min = 5, max = 20, message = "O documento deve ter entre 5 e 20 caracteres.")
        String documento,

        @NotNull(message = "O status é obrigatório.")
        Boolean status,

        Endereco idEndereco // pode ser null, então sem validação
) {}
