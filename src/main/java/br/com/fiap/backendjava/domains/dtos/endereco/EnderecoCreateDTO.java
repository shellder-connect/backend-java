package br.com.fiap.backendjava.domains.dtos.endereco;

public record EnderecoCreateDTO(

        String rua,
        String numero,
        String bairro,
        String cidade,
        String estado,
        String cep,
        String complemento
) {
}
