package br.com.fiap.backendjava.mappers;

import br.com.fiap.backendjava.domains.Abrigo;
import br.com.fiap.backendjava.gateways.dtos.abrigo.AbrigoCreateDTO;
import br.com.fiap.backendjava.gateways.dtos.abrigo.AbrigoDetailDTO;
import br.com.fiap.backendjava.gateways.dtos.abrigo.AbrigoUpdateDTO;
import org.springframework.stereotype.Component;

@Component
public class AbrigoMapper {

    public Abrigo toEntityFromCreateDTO(AbrigoCreateDTO dto) {
        return new Abrigo(null, dto.capacidadeTotal(), dto.ocupacaoAtual(), dto.descricao());
    }

    public void toUpdateEntityFromDTO(AbrigoUpdateDTO dto, Abrigo abrigo) {
        abrigo.setCapacidadeTotal(dto.capacidadeTotal());
        abrigo.setOcupacaoAtual(dto.ocupacaoAtual());
        abrigo.setDescricao(dto.descricao());
    }


    public AbrigoDetailDTO toDetailDTO(Abrigo abrigo) {
        return new AbrigoDetailDTO(
                abrigo.getId(),
                abrigo.getCapacidadeTotal(),
                abrigo.getOcupacaoAtual(),
                abrigo.getDescricao()
        );
    }
}
