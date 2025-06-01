package br.com.fiap.backendjava.mappers;

import br.com.fiap.backendjava.domains.Endereco;
import br.com.fiap.backendjava.domains.User;
import br.com.fiap.backendjava.gateways.dtos.user.UserCreateDTO;
import br.com.fiap.backendjava.gateways.dtos.user.UserDetailDTO;
import br.com.fiap.backendjava.gateways.dtos.user.UserUpdateDTO;
import org.springframework.stereotype.Component;

import static br.com.fiap.backendjava.utils.FormatUtil.formatarNomeCompleto;
import static br.com.fiap.backendjava.utils.FormatUtil.limparTelefone;

@Component
public class UserMapper {

    public User toEntityFromCreateDTO(UserCreateDTO dto, Endereco endereco, String senhaCriptografada) {
        return new User(
                null,
                formatarNomeCompleto(dto.nome()),
                dto.username(),
                senhaCriptografada,
                dto.role(),
                limparTelefone(dto.telefone()),
                endereco,
                dto.dataNascimento(),
                dto.documento(),
                dto.status()
        );
    }

    public void updateEntityFromDTO(UserUpdateDTO dto, User user) {
        user.setNome(formatarNomeCompleto(dto.nome()));
        user.setTelefone(limparTelefone(dto.telefone()));
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

