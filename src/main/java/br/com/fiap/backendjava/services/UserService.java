package br.com.fiap.backendjava.services;

import br.com.fiap.backendjava.domains.User;
import br.com.fiap.backendjava.gateways.dtos.user.UserCreateDTO;
import br.com.fiap.backendjava.gateways.dtos.user.UserDetailDTO;
import br.com.fiap.backendjava.gateways.dtos.user.UserUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    User criar(UserCreateDTO userCreateDTO);

    UserDetailDTO buscarPorId(Integer id);

    Optional<User> buscarPorUsername(String username);

    Page<UserDetailDTO> buscarTodos(Pageable pageable);

    User atualizar(Integer id, UserUpdateDTO userUpdateDTO);

    Boolean deletar(Integer id);
}
