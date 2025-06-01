package br.com.fiap.backendjava.gateways.dtos.user;

import br.com.fiap.backendjava.domains.Endereco;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserUpdateDTO(

        @NotBlank(message = "O nome é obrigatório.")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
        String nome,

        @Pattern(regexp = "\\(?\\d{2}\\)?\\s?9?\\d{4}-\\d{4}", message = "Telefone inválido.")
        String telefone,

        Endereco idEndereco, // pode continuar como null

        @Past(message = "A data de nascimento deve ser no passado.")
        LocalDate dataNascimento,

        @Size(min = 5, max = 20, message = "O documento deve ter entre 5 e 20 caracteres.")
        String documento,

        @NotNull(message = "O status é obrigatório.")
        Boolean status
) {}
