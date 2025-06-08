package br.com.fiap.backendjava.gateways.dtos.user;

import br.com.fiap.backendjava.domains.Endereco;
import br.com.fiap.backendjava.domains.enums.Role;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserDetailDTO(
        Integer id,
        String nome,
        String username,
        String telefone,
        Endereco idEndereco,
        LocalDate dataNascimento,
        Role role,
        String documento,
        Boolean status
) {
}
