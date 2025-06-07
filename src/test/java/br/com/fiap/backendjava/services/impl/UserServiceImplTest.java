package br.com.fiap.backendjava.services.impl;

import br.com.fiap.backendjava.domains.Endereco;
import br.com.fiap.backendjava.domains.User;
import br.com.fiap.backendjava.domains.enums.Role;
import br.com.fiap.backendjava.gateways.dtos.user.UserCreateDTO;
import br.com.fiap.backendjava.gateways.repositories.UserRepository;
import br.com.fiap.backendjava.mappers.UserMapper;
import br.com.fiap.backendjava.security.UserDetailsImpl;
import br.com.fiap.backendjava.security.UserDetailsServiceImpl;
import br.com.fiap.backendjava.services.EmailService;
import br.com.fiap.backendjava.services.exception.AuthorizationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userService = new UserServiceImpl(repository, mapper, passwordEncoder, emailService);
    }

    @Test
    void deveCriarUsuarioComSucesso() {
        // Arrange
        UserCreateDTO dto = new UserCreateDTO(
                "Maria", "maria@email.com", "senha123", Role.PROFISSIONALSAUDE,
                "11999999999", LocalDate.of(1990, 1, 1), "12345678901", true, null
        );

        String senhaCriptografada = "senhaCriptografada";
        User usuarioMapeado = new User();
        usuarioMapeado.setId(1);
        usuarioMapeado.setNome("Maria");
        usuarioMapeado.setUsername("maria@email.com");

        try (MockedStatic<UserDetailsServiceImpl> mockedStatic = mockStatic(UserDetailsServiceImpl.class)) {
            UserDetailsImpl fakeAuthUser = mock(UserDetailsImpl.class);
            mockedStatic.when(UserDetailsServiceImpl::getAuthenticatedUserDetails).thenReturn(fakeAuthUser);

            when(passwordEncoder.encode("senha123")).thenReturn(senhaCriptografada);
            when(mapper.toEntityFromCreateDTO(dto, null, senhaCriptografada)).thenReturn(usuarioMapeado);
            when(repository.save(usuarioMapeado)).thenReturn(usuarioMapeado);

            // Act
            User resultado = userService.criar(dto);

            // Assert
            assertNotNull(resultado);
            assertEquals(1, resultado.getId());

            verify(emailService).enviarEmail("maria@email.com", "Cadastro Realizado", "Usuario Maria criado com sucesso!");
        }
    }

    @Test
    void deveLancarExcecaoQuandoNaoAdminTentaCriarAdmin() {
        // Arrange
        UserCreateDTO dto = new UserCreateDTO(
                "Admin Novo", "newadmin@email.com", "senha123", Role.ADMIN,
                "11999999999", LocalDate.of(1990, 1, 1), "12345678901", true, null
        );

        try (MockedStatic<UserDetailsServiceImpl> mockedStatic = mockStatic(UserDetailsServiceImpl.class)) {
            UserDetailsImpl fakeAuthUser = mock(UserDetailsImpl.class);
            when(fakeAuthUser.hasRole(Role.ADMIN)).thenReturn(false);
            mockedStatic.when(UserDetailsServiceImpl::getAuthenticatedUserDetails).thenReturn(fakeAuthUser);

            // Act & Assert
            assertThrows(AuthorizationException.class, () -> userService.criar(dto),
                    "Deveria lançar AuthorizationException quando não-admin tenta criar admin");

            verify(repository, never()).save(any(User.class));
            verify(mapper, never()).toEntityFromCreateDTO(any(UserCreateDTO.class), any(Endereco.class), anyString());
            verify(emailService, never()).enviarEmail(anyString(), anyString(), anyString());
        }
    }

    @Test
    void deveCriarAdminQuandoUsuarioLogadoEhAdmin() {
        // Arrange
        UserCreateDTO dto = new UserCreateDTO(
                "Super Admin", "superadmin@email.com", "senha123", Role.ADMIN,
                "11999999999", LocalDate.of(1990, 1, 1), "12345678901", true, null
        );

        String senhaCriptografada = "senhaCriptografada";
        User usuarioMapeado = new User();
        usuarioMapeado.setId(2);
        usuarioMapeado.setNome("Super Admin");
        usuarioMapeado.setUsername("superadmin@email.com");

        try (MockedStatic<UserDetailsServiceImpl> mockedStatic = mockStatic(UserDetailsServiceImpl.class)) {
            UserDetailsImpl fakeAuthUser = mock(UserDetailsImpl.class);
            when(fakeAuthUser.hasRole(Role.ADMIN)).thenReturn(true);
            mockedStatic.when(UserDetailsServiceImpl::getAuthenticatedUserDetails).thenReturn(fakeAuthUser);

            when(passwordEncoder.encode("senha123")).thenReturn(senhaCriptografada);
            when(mapper.toEntityFromCreateDTO(dto, null, senhaCriptografada)).thenReturn(usuarioMapeado);
            when(repository.save(usuarioMapeado)).thenReturn(usuarioMapeado);

            // Act
            User resultado = userService.criar(dto);

            // Assert
            assertNotNull(resultado);
            assertEquals(2, resultado.getId());
            assertEquals("Super Admin", resultado.getNome());
            assertEquals("superadmin@email.com", resultado.getUsername());

            verify(emailService).enviarEmail("superadmin@email.com", "Cadastro Realizado", "Usuario Super Admin criado com sucesso!");

        }
    }
}
