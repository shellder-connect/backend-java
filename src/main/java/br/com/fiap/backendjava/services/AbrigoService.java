package br.com.fiap.backendjava.services;

import br.com.fiap.backendjava.domains.Abrigo;
import br.com.fiap.backendjava.gateways.dtos.abrigo.AbrigoCreateDTO;
import br.com.fiap.backendjava.gateways.dtos.abrigo.AbrigoDetailDTO;
import br.com.fiap.backendjava.gateways.dtos.abrigo.AbrigoUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AbrigoService {

    Abrigo criar(AbrigoCreateDTO abrigoCreateDTO);

    Abrigo buscarPorId(Integer id);

    List<Abrigo> buscarTodos();

    Page<AbrigoDetailDTO> findPage(Pageable pageable, String descricao);

    Abrigo atualizar(Integer id, AbrigoUpdateDTO abrigoUpdateDTO);

    Boolean deletar(Integer id);

    String gerarResumoAbrigos(List<Abrigo> abrigos);
}
