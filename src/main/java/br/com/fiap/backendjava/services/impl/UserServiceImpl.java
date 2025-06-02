package br.com.fiap.backendjava.services.impl;

import br.com.fiap.backendjava.domains.Endereco;
import br.com.fiap.backendjava.domains.User;
import br.com.fiap.backendjava.domains.enums.Role;
import br.com.fiap.backendjava.gateways.dtos.user.UserCreateDTO;
import br.com.fiap.backendjava.gateways.dtos.user.UserDetailDTO;
import br.com.fiap.backendjava.gateways.dtos.user.UserUpdateDTO;
import br.com.fiap.backendjava.gateways.repositories.UserRepository;
import br.com.fiap.backendjava.mappers.UserMapper;
import br.com.fiap.backendjava.security.UserDetailsImpl;
import br.com.fiap.backendjava.security.UserDetailsServiceImpl;
import br.com.fiap.backendjava.services.UserService;
import br.com.fiap.backendjava.services.exception.AuthorizationException;
import br.com.fiap.backendjava.services.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailServiceImpl emailService;

    @Override
    public User criar(UserCreateDTO userCreateDTO) {
        UserDetailsImpl authUser = UserDetailsServiceImpl.getAuthenticatedUserDetails();
        if (userCreateDTO.role() == Role.ADMIN && !authUser.hasRole(Role.ADMIN)) {
            throw new AuthorizationException("Somente administradores podem criar outros administradores.");
        }

        Endereco endereco = userCreateDTO.idEndereco();
        String senhaCriptografada = passwordEncoder.encode(userCreateDTO.senha());

        User user = mapper.toEntityFromCreateDTO(userCreateDTO, endereco, senhaCriptografada);

        String mensagemEmail = String.format("Usuario %s criado com sucesso!", user.getNome());
        emailService.enviarEmail(user.getUsername(), "Cadastro Realizado", mensagemEmail);
        return repository.save(user);
    }

    @Override
    public UserDetailDTO buscarPorId(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado. ID: " + id));

        UserDetailsImpl authUser = UserDetailsServiceImpl.getAuthenticatedUserDetails();
        if (!authUser.hasRole(Role.ADMIN) && !authUser.getId().equals(id)) {
            throw new AuthorizationException("Você só pode visualizar seu próprio usuário");
        }

        return mapper.toDetailDTO(user);
    }

    @Override
    public Optional<User> buscarPorUsername(String username) {
        return repository.findUserByUsername(username);
    }

    @Override
    public Page<UserDetailDTO> buscarTodos(Pageable pageable) {
        UserDetailsImpl authUser = UserDetailsServiceImpl.getAuthenticatedUserDetails();
        if (!authUser.hasRole(Role.ADMIN)) {
            throw new AuthorizationException("Apenas administradores podem visualizar todos os usuários");
        }

        return repository.findAll(pageable).map(mapper::toDetailDTO);
    }

    @Override
    public User atualizar(Integer id, UserUpdateDTO userUpdateDTO) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado. ID: " + id));

        UserDetailsImpl authUser = UserDetailsServiceImpl.getAuthenticatedUserDetails();
        if (!authUser.hasRole(Role.ADMIN) && !authUser.getId().equals(id)) {
            throw new AuthorizationException("Você só pode atualizar seu próprio usuário");
        }

        mapper.updateEntityFromDTO(userUpdateDTO, user);
        return repository.save(user);
    }

    @Override
    public Boolean deletar(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado. ID: " + id));

        UserDetailsImpl authUser = UserDetailsServiceImpl.getAuthenticatedUserDetails();
        if (!authUser.hasRole(Role.ADMIN) && !authUser.getId().equals(id)) {
            throw new AuthorizationException("Você só pode deletar sua própria conta");
        }

        repository.deleteById(id);
        return true;
    }
}
