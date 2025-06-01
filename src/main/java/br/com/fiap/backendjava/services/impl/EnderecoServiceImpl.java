package br.com.fiap.backendjava.services.impl;

import br.com.fiap.backendjava.domains.Endereco;
import br.com.fiap.backendjava.domains.User;
import br.com.fiap.backendjava.gateways.dtos.endereco.EnderecoCreateOrUpdateDTO;
import br.com.fiap.backendjava.domains.enums.Role;
import br.com.fiap.backendjava.gateways.repositories.EnderecoRepository;
import br.com.fiap.backendjava.gateways.repositories.UserRepository;
import br.com.fiap.backendjava.mappers.EnderecoMapper;
import br.com.fiap.backendjava.security.UserDetailsImpl;
import br.com.fiap.backendjava.security.UserDetailsServiceImpl;
import br.com.fiap.backendjava.services.EnderecoService;
import br.com.fiap.backendjava.services.exception.AuthorizationException;
import br.com.fiap.backendjava.services.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository repository;
    private final UserRepository userRepository;
    private final EnderecoMapper mapper;

    @Override
    public Endereco criar(EnderecoCreateOrUpdateDTO enderecoCreateDTO) {
        return repository.save(mapper.toEntityFromEnderecoCreateDTO(enderecoCreateDTO));
    }

    @Override
    public Endereco buscarPorId(Integer id) {
        Endereco endereco = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));

        UserDetailsImpl authUser = UserDetailsServiceImpl.getAuthenticatedUserDetails();
        if (!authUser.hasRole(Role.ADMIN)) {
            User user = userRepository.findById(authUser.getId())
                    .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
            if (user.getIdEndereco() == null || !user.getIdEndereco().getId().equals(id)) {
                throw new AuthorizationException("Você só pode visualizar seu próprio endereço");
            }
        }
        return endereco;
    }

    @Override
    public Endereco atualizar(Integer id, EnderecoCreateOrUpdateDTO enderecoUpdateDTO) {
        Endereco endereco = buscarPorId(id);
        UserDetailsImpl authUser = UserDetailsServiceImpl.getAuthenticatedUserDetails();
        if (!authUser.hasRole(Role.ADMIN)) {
            User user = userRepository.findById(authUser.getId())
                    .orElseThrow(() -> new ObjectNotFoundException("Usuário de id " + id + " não encontrado"));

            if (user.getIdEndereco() == null || !user.getIdEndereco().getId().equals(id)) {
                throw new AuthorizationException("Você só pode atualizar seu próprio endereço");
            }
        }
        mapper.toUpdateEntityFromDTO(enderecoUpdateDTO, endereco);
        return repository.save(endereco);
    }

    @Override
    public Void deletar(Integer id) {
        UserDetailsImpl authUser = UserDetailsServiceImpl.getAuthenticatedUserDetails();
        if (!authUser.hasRole(Role.ADMIN)) {
            User user = userRepository.findById(authUser.getId())
                    .orElseThrow(() -> new ObjectNotFoundException("Usuário de id " + id + " não encontrado"));

            if (user.getIdEndereco() == null || !user.getIdEndereco().getId().equals(id)) {
                throw new AuthorizationException("Você só pode deletar seu próprio endereço");
            }
        }
        repository.deleteById(id);
        return null;
    }
}
