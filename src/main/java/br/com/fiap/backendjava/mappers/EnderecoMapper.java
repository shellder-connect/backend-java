package br.com.fiap.backendjava.mappers;

import br.com.fiap.backendjava.domains.Endereco;
import br.com.fiap.backendjava.gateways.dtos.endereco.EnderecoCreateOrUpdateDTO;
import br.com.fiap.backendjava.gateways.dtos.endereco.EnderecoDetailDTO;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

    public Endereco toEntityFromEnderecoCreateDTO(EnderecoCreateOrUpdateDTO dto) {
        return new Endereco(
                null,
                dto.rua(),
                dto.numero(),
                dto.bairro(),
                dto.cidade(),
                dto.estado(),
                dto.cep(),
                dto.complemento()
        );
    }

    public EnderecoDetailDTO toEnderecoDetailDTOFromEntity(Endereco endereco) {
        return new EnderecoDetailDTO(
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep(),
                endereco.getComplemento()
        );
    }

    public void toUpdateEntityFromDTO(EnderecoCreateOrUpdateDTO dto, Endereco endereco) {
        endereco.setRua(dto.rua());
        endereco.setNumero(dto.numero());
        endereco.setBairro(dto.bairro());
        endereco.setCidade(dto.cidade());
        endereco.setEstado(dto.estado());
        endereco.setCep(dto.cep());
        endereco.setComplemento(dto.complemento());
    }
}
