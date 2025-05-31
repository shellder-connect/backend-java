package br.com.fiap.backendjava.gateways.dtos.user;

import br.com.fiap.backendjava.domains.Endereco;

import java.time.LocalDate;

public record UserSimpleDetailDTO(
        String nome,
        String username,
        String telefone,
        LocalDate dataNascimento,
        Boolean status
) {
}
