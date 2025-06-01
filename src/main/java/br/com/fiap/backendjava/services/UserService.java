package br.com.fiap.backendjava.services;

import br.com.fiap.backendjava.domains.User;
import br.com.fiap.backendjava.gateways.dtos.user.UserCreateDTO;
import br.com.fiap.backendjava.gateways.dtos.user.UserDetailDTO;
import br.com.fiap.backendjava.gateways.dtos.user.UserUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User criar(UserCreateDTO userCreateDTO);

    User buscarPorId(Integer id);

    Optional<User> buscarPorUsername(String username);

    List<UserDetailDTO> buscarTodos();

    User atualizar(Integer id, UserUpdateDTO userUpdateDTO);

    Boolean deletar(Integer id);
}
