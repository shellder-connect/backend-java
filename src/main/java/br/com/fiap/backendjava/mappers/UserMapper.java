package br.com.fiap.backendjava.mappers;

import br.com.fiap.backendjava.domains.Endereco;
import br.com.fiap.backendjava.domains.User;
import br.com.fiap.backendjava.gateways.dtos.user.UserCreateDTO;
import br.com.fiap.backendjava.gateways.dtos.user.UserDetailDTO;
import br.com.fiap.backendjava.gateways.dtos.user.UserUpdateDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntityFromCreateDTO(UserCreateDTO dto, Endereco endereco, String senhaCriptografada) {
        return new User(
                null,
                dto.nome(),
                dto.username(),
                senhaCriptografada,
                dto.role(),
                dto.telefone(),
                endereco,
                dto.dataNascimento(),
                dto.documento(),
                dto.status()
        );
    }

    public void updateEntityFromDTO(UserUpdateDTO dto, User user) {
        user.setNome(dto.nome());
        user.setUsername(dto.username());
        user.setTelefone(dto.telefone());
        user.setDataNascimento(dto.dataNascimento());
        user.setDocumento(dto.documento());
        user.setStatus(dto.status());
    }

    public UserDetailDTO toDetailDTO(User user) {
        return new UserDetailDTO(
                user.getId(),
                user.getNome(),
                user.getUsername(),
                user.getTelefone(),
                user.getIdEndereco(),
                user.getDataNascimento(),
                user.getDocumento(),
                user.getStatus()
        );
    }
}

