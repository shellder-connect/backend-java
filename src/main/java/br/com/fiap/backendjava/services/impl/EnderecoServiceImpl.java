package br.com.fiap.backendjava.services.impl;

import br.com.fiap.backendjava.domains.Endereco;
import br.com.fiap.backendjava.gateways.repositories.EnderecoRepository;
import br.com.fiap.backendjava.services.EnderecoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.ObjectNotFoundException;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class EnderecoServiceImpl implements EnderecoService {

    private EnderecoRepository repository;

    @Override
    public Endereco criar(Endereco endereco) {
        return null;
    }

    @Override
    public Endereco buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName(), null));
    }

    @Override
    public List<Endereco> buscarTodos() {
        return List.of();
    }

    @Override
    public Endereco atualizar(Integer id, Endereco endereco) {
        return null;
    }

    @Override
    public Boolean deletar(Integer id) {
        return null;
    }
}
