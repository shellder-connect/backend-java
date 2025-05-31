package br.com.fiap.backendjava.domains.dtos.user;

import br.com.fiap.backendjava.domains.Endereco;

import java.time.LocalDate;

public record UserUpdateDTO(
        String nome,
        String username,
        String telefone,
        Endereco idEndereco,
        LocalDate dataNascimento,
        String documento,
        Boolean status
) {
}
