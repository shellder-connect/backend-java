package br.com.fiap.backendjava.services;

import br.com.fiap.backendjava.domains.Endereco;
import br.com.fiap.backendjava.gateways.dtos.endereco.EnderecoCreateOrUpdateDTO;

public interface EnderecoService {

    Endereco criar(EnderecoCreateOrUpdateDTO enderecoCreateDTO);

    Endereco buscarPorId(Integer id);

    Endereco atualizar(Integer id, EnderecoCreateOrUpdateDTO enderecoUpdateDTO);

    Void deletar(Integer id);
}
