package br.com.fiap.backendjava.services.impl;

import br.com.fiap.backendjava.domains.Endereco;
import br.com.fiap.backendjava.domains.User;
import br.com.fiap.backendjava.domains.dtos.user.UserCreateDTO;
import br.com.fiap.backendjava.domains.dtos.user.UserDetailDTO;
import br.com.fiap.backendjava.domains.dtos.user.UserUpdateDTO;
import br.com.fiap.backendjava.domains.enums.Role;
import br.com.fiap.backendjava.gateways.repositories.EnderecoRepository;
import br.com.fiap.backendjava.gateways.repositories.UserRepository;
import br.com.fiap.backendjava.mappers.EnderecoMapper;
import br.com.fiap.backendjava.mappers.UserMapper;
import br.com.fiap.backendjava.security.UserDetailsImpl;
import br.com.fiap.backendjava.security.UserDetailsServiceImpl;
import br.com.fiap.backendjava.services.UserService;
import br.com.fiap.backendjava.services.exception.AuthorizationException;
import br.com.fiap.backendjava.services.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository repository;
    private EnderecoRepository enderecoRepository;
    private UserMapper mapper;
    private EnderecoMapper enderecoMapper;
    private PasswordEncoder passwordEncoder;


    @Override
    public User criar(UserCreateDTO userCreateDTO) {
        UserDetailsImpl authUser = UserDetailsServiceImpl.getAuthenticatedUserDetails();
        if (userCreateDTO.role() == Role.ADMIN && !authUser.hasRole(Role.ADMIN)) {
            throw new AuthorizationException("Somente administradores podem criar outros administradores.");
        }
        Endereco endereco = userCreateDTO.idEndereco();
        String senhaCriptografada = passwordEncoder.encode(userCreateDTO.senha());
        User user = mapper.toEntityFromCreateDTO(userCreateDTO, endereco, senhaCriptografada);
        return repository.save(user);
    }

    @Override
    public User buscarPorId(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado. ID: " + id));

        UserDetailsImpl authUser = UserDetailsServiceImpl.getAuthenticatedUserDetails();
        if (!authUser.hasRole(Role.ADMIN) && !authUser.getId().equals(id)) {
            throw new AuthorizationException("Você só pode visualizar seu próprio usuário");
        }
        return user;
    }

    @Override
    public List<UserDetailDTO> buscarTodos() {
        UserDetailsImpl authUser = UserDetailsServiceImpl.getAuthenticatedUserDetails();
        if (!authUser.hasRole(Role.ADMIN)) {
            throw new AuthorizationException("Apenas administradores podem visualizar todos os usuários");
        }
        return repository.findAll().stream()
                .map(mapper::toDetailDTO)
                .collect(Collectors.toList());
    }

    @Override
    public User atualizar(Integer id, UserUpdateDTO userUpdateDTO) {
        User user = buscarPorId(id);

        UserDetailsImpl authUser = UserDetailsServiceImpl.getAuthenticatedUserDetails();
        if (!authUser.hasRole(Role.ADMIN) && !authUser.getId().equals(id)) {
            throw new AuthorizationException("Você só pode atualizar seu próprio usuário");
        }

        mapper.updateEntityFromDTO(userUpdateDTO, user);
        return repository.save(user);
    }


    @Override
    public Boolean deletar(Integer id) {
        User user = buscarPorId(id);
        UserDetailsImpl authUser = UserDetailsServiceImpl.getAuthenticatedUserDetails();
        if (!authUser.hasRole(Role.ADMIN) && !authUser.getId().equals(id)) {
            throw new AuthorizationException("Você só pode deletar sua própria conta");
        }
        repository.deleteById(id);
        return true;
    }
}
