package br.com.fiap.backendjava.services.impl;

import br.com.fiap.backendjava.domains.User;
import br.com.fiap.backendjava.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public User criar(UserCreateDTO userCreateDTO) {
        return null;
    }

    @Override
    public User buscarPorId(Integer id) {
        return null;
    }

    @Override
    public List<UserDetailDTO> buscarTodos() {
        return List.of();
    }

    @Override
    public User atualizar(Integer id, UserUpdateDTO userUpdateDTO) {
        return null;
    }

    @Override
    public Boolean deletar(Integer id) {
        return null;
    }
}
